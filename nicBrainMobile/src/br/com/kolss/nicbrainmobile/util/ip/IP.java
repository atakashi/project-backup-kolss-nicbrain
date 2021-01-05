package br.com.kolss.nicbrainmobile.util.ip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.util.Log;

/**
 * Ex.:
 *
 * 
 * AndroidManifest.xml permissions
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 *
 *
 * Fazer testes dos metodos da classe
 * Utils.getMACAddress("wlan0");
 * Utils.getMACAddress("eth0");
 * Utils.getIPAddress(true); // IPv4
 * Utils.getIPAddress(false); // IPv6
 *
 * 
 * TextView tv = (TextView) findViewById(R.id.tv);
 * WifiManager wim= (WifiManager) getSystemService(WIFI_SERVICE);
 * List<WifiConfiguration> l =  wim.getConfiguredNetworks(); 
 * WifiConfiguration wc = l.get(0); 
 * tv.append("\n"+ Formatter.formatIpAddress(wim.getConnectionInfo().getIpAddress()));
 * 
 * 
 * This will tell you your own IP Address if you run this on your Android Phone.
 *
 * try{
 *     InetAddress ownIP=InetAddress.getLocalHost();
 *     System.out.println("IP of my Android := "+ownIP.getHostAddress());
 *     }catch (Exception e){
 *     System.out.println("Exception caught ="+e.getMessage());
 *     }
 *
 *
 * If you want to know the IP address of any the client which binds with your application use
 *
 * Socket socket = serverSocket.accept();  // accept connection                                            
 *
 * System.out.println("Remote IP:"+socket.getInetAddress());
 *
 * System.out.println("Remote Port:"+socket.getPort());  
 *
 * 
 * Though there's a correct answer, I share my answer here and hope that this way will more convenience.
 *
 * WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
 * WifiInfo wifiInf = wifiMan.getConnectionInfo();
 * int ipAddress = wifiInf.getIpAddress();
 * String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
 * 
 * 
 * Recently, an IP address is still returned by getLocalIpAddress() despite being disconnected from the network (no service indicator). It means the IP address displayed in the Settings> About phone> Status was different from what the application thought.
 *
 * I have implemented a workaround by adding this code before:
 *
 * ConnectivityManager cm = getConnectivityManager();
 * NetworkInfo net = cm.getActiveNetworkInfo();
 * if ((null == net) || !net.isConnectedOrConnecting()) {
 *   return null;
 * }
 * 
 * 
 * @author LuisCM
 */
public class IP {

	private static final String TAG = "IP";
	
    /**
     * Convert byte array to hex string
     * 
     * @param bytes byte[]
     * @return String
     */
    public static String bytesToHex(final byte[] bytes) {
        final StringBuilder sbuf = new StringBuilder();
        for(int idx = 0; idx < bytes.length; idx++) {
            final int intVal = (bytes[idx] & 0xff);
            if (intVal < 0x10) {
            	sbuf.append("0");
            }
            sbuf.append(Integer.toHexString(intVal).toUpperCase());
        }
        return sbuf.toString();
    }

    /**
     * Get utf8 byte array.
     * 
     * @param str String
     * @return byte[] array of NULL if error was found
     */
    public static byte[] getUTF8Bytes(final String str) {
        try { 
        	return str.getBytes("UTF-8");
        } catch (final Exception ex) {
        	return null;
        }
    }

    /**
     * Load UTF8withBOM or any ansi text file.
     * 
     * @param filename String
     * @return String
     * @throws java.io.IOException
     */
    public static String loadFileAsString(final String filename) throws java.io.IOException {
    	final int BUFLEN = 1024;
    	final BufferedInputStream is = new BufferedInputStream(new FileInputStream(filename), BUFLEN);
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFLEN);
            final byte[] bytes = new byte[BUFLEN];
            boolean isUTF8 = false;
            int read = 0;
            int count = 0;           
            while((read = is.read(bytes)) != -1) {
                if ((count == 0) && (bytes[0] == ((byte)0xEF)) && (bytes[1] == ((byte)0xBB)) && (bytes[2] == ((byte)0xBF))) {
                    isUTF8 = true;
                    baos.write(bytes, 3, read-3); // drop UTF8 bom marker
                } else {
                    baos.write(bytes, 0, read);
                }
                count += read;
            }
            return (isUTF8 ? new String(baos.toByteArray(), "UTF-8") : new String(baos.toByteArray()));
        } finally {
            try {
            	is.close();
            } catch (final Exception ex) {
            } 
        }
    }

    /**
     * Returns MAC address of the given interface name.
     * 
     * @param interfaceName String interfaceName eth0, wlan0 or NULL=use first interface 
     * @return String mac address or empty string
     */
    public static String getMACAddress(final String interfaceName) {
        try {
            final List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (final NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) {
                    	continue;
                    }
                }
                final byte[] mac = intf.getHardwareAddress();
                if (mac == null) {
                	return "";
                }
                final StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx<mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));       
                if (buf.length() > 0) {
                	buf.deleteCharAt((buf.length() - 1));
                }
                return buf.toString();
            }
        } catch (final Exception ex) {  // for now eat exceptions
        }
        return "";
        /*try {
            // this is so Linux hack
            return loadFileAsString("/sys/class/net/" +interfaceName + "/address").toUpperCase().trim();
        } catch (IOException ex) {
            return null;
        }*/
    }

    /**
     * Get IP address from first non-localhost interface
     * 
     * @param useIPv4 boolean true=return ipv4, false=return ipv6
     * @return String address or empty string
     */
    public static String getIPAddress(final boolean useIPv4) {
        try {
            final List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (final NetworkInterface intf : interfaces) {
                final List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (final InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        final String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
                        if (useIPv4) {
                            if (isIPv4) { 
                                return sAddr;
                            }
                        } else {
                            if (!isIPv4) {
                                final int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return (delim < 0 ? sAddr : sAddr.substring(0, delim));
                            }
                        }
                    }
                }
            }
        } catch (final Exception ex) {  // for now eat exceptions
        }
        return "";
    }

    /**
     * 
     * @return String
     */
    public static String getLocalIpAddress() {
        try {
            for (final Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                final NetworkInterface intf = en.nextElement();
                for (final Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    final InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                        //final String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        //Log.i(TAG, "*** IP = "+ ip);
                        //return ip;
                    }
                }
            }
        } catch (final SocketException ex) {
            Log.e(TAG, ex.toString());
            //ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
     * WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
     * int ip = wifiInfo.getIpAddress();
     * String ipAddress = Formatter.formatIpAddress(ip);
     *
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     * 
     * @param context Context
     * @return String
     */
    protected String wifiIpAddress(Context context) {
    	return null;
//        final WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
//        final int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
//        // Convert little-endian to big-endian if needed
//        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
//            ipAddress = Integer.reverseBytes(ipAddress);
//        }
//        final byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
//        final String ipAddressString;
//        try {
//            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
//        } catch (final UnknownHostException ex) {
//            Log.e("WIFIIP", "Unable to get host address.");
//            ipAddressString = null;
//        }
//        return ipAddressString;
    	
/////////////////////////////////////////////////////////////////////////////////////    	
    	
//    	WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
//    	WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//    	int ipAddress = wifiInfo.getIpAddress();
//    	String ip = intToIp(ipAddress);
//    	 
//    	....................
//    	 
//    	public String intToIp(int i) {
//    	   return ((i >> 24 ) & 0xFF ) + "." +
//    	               ((i >> 16 ) & 0xFF) + "." +
//    	               ((i >> 8 ) & 0xFF) + "." +
//    	               ( i & 0xFF) ;
//    	}
//    	
    	
    }    
}