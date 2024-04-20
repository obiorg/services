/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.obi.services.core.moka7.IntByRef;
import org.obi.services.core.moka7.S7;
import org.obi.services.core.moka7.S7Client;
import org.obi.services.core.moka7.S7CpInfo;
import org.obi.services.core.moka7.S7CpuInfo;
import org.obi.services.core.moka7.S7OrderCode;
import org.obi.services.core.moka7.S7Szl;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.tags.Tags;
import org.obi.services.util.Util;
import org.obi.services.listener.machines.MachinesListener;

/**
 *
 * @author r.hendrick
 */
public class MachineConnection extends Thread implements MachinesListener {

    //!< Default machine for connection
    private Machines machine;

    //!< If MakeAllTests = true, also DBWrite and Run/Stop tests will be performed
    private final boolean MakeAllTests = true;

    private long Elapsed;   //!< Measuring elapsed time during operation
    private byte[] Buffer = new byte[65536]; // 64K buffer (maximum for S7400 systems)
    private S7Client client = new S7Client();   //!< Main client

    private int ok = 0;
    private int ko = 0;

    private int DataToMove; // Data size to read/write
    private int currentPLCStatus = S7.S7CpuStatusUnknown;

    private Boolean connected = false;
    private int PDULength = 0;

    private int errorCode = 0;
    private HashMap<Integer, String> errors = new HashMap<Integer, String>();

    private Boolean requestConnection = false;
    private Boolean requestDateTime = false;
    private Boolean requestOrderCode = false;
    private Boolean requestStop = false;
    private Boolean running = false;

    private Boolean requestPLCStatus = false;
    private Boolean requestCpuInfo = false;
    private Boolean requestCpInfo = false;
    private Boolean requestSzl = false;
    private boolean requestConnectionState;

    /**
     * Array list which contain all the connection listeners that should receive
     * event from client class
     */
    private ArrayList<MachinesListener> connectionListeners = new ArrayList<>();

    /**
     * Allow to add connexion listener to the list of event listener
     *
     * @param connectionListener a class which will listen to service event
     */
    public void addClientListener(MachinesListener connectionListener) {
        connectionListeners.add(connectionListener);
    }

    /**
     * Allow to remove connexion listener to the list of event listener
     *
     * @param connectionListener a class which will listen to service event
     */
    public void removeClientListener(MachinesListener connectionListener) {
        connectionListeners.remove(connectionListener);
    }

    /**
     * Default constructor machine connection
     */
    public MachineConnection() {

    }

    /**
     * Constructor allow to specify main value of machine suppose default config
     * for rack = 0 and slot 2
     *
     * @param address ip address of machine
     * @param rack rack of the machine (usually 0)
     * @param slot slot of the machine reference (usually 2)
     */
    public MachineConnection(String address, Integer rack, Integer slot) {
        machine.setAddress(address);
        machine.setRack(rack);
        machine.setSlot(slot);
    }

    /**
     * Surcharge constructor allow to directly specify machine to be considered
     *
     * @param machine machine to be used
     */
    public MachineConnection(Machines machine) {
        this.machine = machine;
    }

    public Boolean getRequestStop() {
        return requestStop;
    }

    public void setRequestStop(Boolean requestStop) {
        this.requestStop = requestStop;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    /**
     * Read corresponding error code to deliver a description of it. The error
     * is register in the map key error.
     *
     * @return error as text
     */
    public String getErrorText() {
        String err = S7Client.ErrorText(errorCode);
        errors.put(errorCode, err);

        for (int i = 0; i < connectionListeners.size(); i++) {
            connectionListeners.get(i).onNewError(errorCode, err);
        }
        return err;
    }

    /**
     * Convenient method to standarize display on start fonction by specifed
     * FunctionName
     *
     * @param FunctionName name of the fonction to present at start
     * @return value of current time in millisec
     */
    private long begin(String FunctionName) {
//        Util.out("");
//        Util.out("MachineConnection : begin >> +================================================================");
//        Util.out("MachineConnection : begin >> | " + FunctionName);
//        Util.out("MachineConnection : begin >> +================================================================");
        Elapsed = System.currentTimeMillis();
        return Elapsed;
    }

    /**
     * Convenient method to standarize display on end fonction by specified code
     * error. During this step it also count number of error and good execution
     * function
     *
     * @param error code error to analyse
     *
     * @return duration time of processing
     */
    private long end(int error) {
        if (error != 0) {
            ko++;
        } else {
            ok++;
        }
        long duration = (System.currentTimeMillis() - Elapsed);
//        Util.out("MachineConnection : end >> Execution time " + duration + " ms");
        return duration;
    }

    /**
     * Connection initialize with connection type than try to connect over
     * machine.
     *
     * @return true if connection has been processed
     */
    public Boolean doConnect() {
        begin("doConnect...");

        if (client.Connected) {
            return true;
        } else { // try to connect
            connected = false;

            // SELECT Connection type depend on machine driver
            byte connectionType = S7.OP;
            switch (machine.getDriver().getId()) {
                case 4: // IM151-
                    connectionType = S7.PG;
                    break;
                default: // S7300 / S7400 / WinAC / S71200 / S71500
                    connectionType = S7.OP;
                    break;
            }

            // Process to connection type and connection
            client.SetConnectionType(connectionType);
            errorCode = client.ConnectTo(machine.getAddress(),
                    machine.getRack(),
                    machine.getSlot());

            if (errorCode == 0) {
                PDULength = client.PDULength();

                for (int i = 0; i < connectionListeners.size(); i++) {
                    connectionListeners.get(i).onConnectionSucced(machine, (int) end(errorCode), getErrorText());
                    connectionListeners.get(i).onPDUUpdate(client.PDULength());
                }
                connected = true;
            } else { // Error on connection !
                for (int i = 0; i < connectionListeners.size(); i++) {
                    connectionListeners.get(i).onConnectionError(machine, (int) end(errorCode), getErrorText());
                    connectionListeners.get(i).onNewError(errorCode, getErrorText());
                }
                return connected;
            }
        }
        return true;
    }

    /**
     * Connection initialize with connection type than try to connect over
     * machine.
     *
     */
    public void doConnectState() {
        begin("doConnectState...");

        Util.out("MachineConnection : doConnectState >> Connected to   : "
                + machine.getAddress() + " (Rack="
                + machine.getRack() + ", Slot="
                + machine.getSlot() + ")");
        Util.out("MachineConnection : doConnect >> PDU negotiated : "
                + PDULength + " bytes");

        for (int i = 0; i < connectionListeners.size(); i++) {
            connectionListeners.get(i).onConnectionSucced(machine, (int) errorCode, getErrorText());
            connectionListeners.get(i).onPDUUpdate(PDULength);
        }

    }

    /**
     * Recover date and time on previously connected connection. Mean doConnect
     * need to be call previously
     */
    private void doGetDateAndTime() {
        Date PlcDateTime = new Date();
        begin("doGetDateAndTime()");
        if (connected = true) {
            errorCode = client.GetPlcDateTime(PlcDateTime);
            if (errorCode == 0) {
                Util.out("MachineConnection : doGetDateAndTime >> CPU Date/Time : "
                        + PlcDateTime);
                for (int i = 0; i < connectionListeners.size(); i++) {
                    connectionListeners.get(i).onDateTimeResponse(PlcDateTime);
                }
            }
        } else {
            Util.out("MachineConnection : doGetDateAndTime >> Please connect first !");
        }
        end(errorCode);
    }

    /**
     * Get Order Code of component.
     *
     * @return order code containing code and firmware version
     */
    public S7OrderCode doGetOrderCode() {
        begin("doGetOrderCode()");
        S7OrderCode orderCode = new S7OrderCode();
        if (connected = true) {
            errorCode = client.GetOrderCode(orderCode);
            if (errorCode == 0) {
                System.out.println("Order Code        : " + orderCode.Code());
                System.out.println("Firmware version  : " + orderCode.V1 + "." + orderCode.V2 + "." + orderCode.V3);
                for (int i = 0; i < connectionListeners.size(); i++) {
                    connectionListeners.get(i).onOrderCodeResponse(orderCode);
                }
            } else {
                System.out.println("Error " + errorCode + " : " + getErrorText());

                return null;
            }
        } else {
            Util.out("MachineConnection : doGetDateAndTime >> Please connect first !");
            return null;
        }
        end(errorCode);

        return orderCode;
    }

    /**
     * Allow to display PLC status RUN / STOP / UNKNOW CODE
     */
    public void doGetPLCStatus() {
        begin("doGetPlcStatus()");
        IntByRef PlcStatus = new IntByRef(S7.S7CpuStatusUnknown);
        int Result = client.GetPlcStatus(PlcStatus);
        if (Result == 0) {

            System.out.print("PLC Status : ");
            switch (PlcStatus.Value) {
                case S7.S7CpuStatusRun:
                    System.out.println("RUN");
                    break;
                case S7.S7CpuStatusStop:
                    System.out.println("STOP");
                    break;
                default:
                    System.out.println("Unknown (" + PlcStatus.Value + ")");
            }
            for (int i = 0; i < connectionListeners.size(); i++) {
                connectionListeners.get(i).onPLCStatusResponse(PlcStatus);
            }
        }
        currentPLCStatus = PlcStatus.Value;
        end(Result);
    }

    /**
     * Allow to get CPU information
     */
    public void doGetCpuInfo() {
        begin("doGetCpuInfo()");
        S7CpuInfo CpuInfo = new S7CpuInfo();
        int Result = client.GetCpuInfo(CpuInfo);
        if (Result == 0) {
            System.out.println("Module Type Name  : " + CpuInfo.ModuleTypeName());
            System.out.println("Serial Number     : " + CpuInfo.SerialNumber());
            System.out.println("AS Name           : " + CpuInfo.ASName());
            System.out.println("CopyRight         : " + CpuInfo.Copyright());
            System.out.println("Module Name       : " + CpuInfo.ModuleName());
            for (int i = 0; i < connectionListeners.size(); i++) {
                connectionListeners.get(i).onPLCInfoResponse(CpuInfo);
            }
        }
        end(Result);
    }

    /**
     * Allow to get CP information
     */
    public void doGetCpInfo() {
        begin("doGetCpInfo()");
        S7CpInfo CpInfo = new S7CpInfo();
        int Result = client.GetCpInfo(CpInfo);
        if (Result == 0) {
            System.out.println("Max PDU Length    : " + CpInfo.MaxPduLength);
            System.out.println("Max connections   : " + CpInfo.MaxConnections);
            System.out.println("Max MPI rate (bps): " + CpInfo.MaxMpiRate);
            System.out.println("Max Bus rate (bps): " + CpInfo.MaxBusRate);
            for (int i = 0; i < connectionListeners.size(); i++) {
                connectionListeners.get(i).onCpInfoResponse(CpInfo);
            }
        }
        end(Result);
    }

    /**
     * Allow to get Szl information
     */
    public void doReadSzl() {
        S7Szl SZL = new S7Szl(1024);
        begin("doReadSZL() - ID : 0x0011, IDX : 0x0000");
        int Result = client.ReadSZL(0x0011, 0x0000, SZL);
        if (Result == 0) {
            System.out.println("LENTHDR : " + SZL.LENTHDR);
            System.out.println("N_DR    : " + SZL.N_DR);
            System.out.println("Size    : " + SZL.DataSize);
            HexDump(SZL.Data, SZL.DataSize);
            for (int i = 0; i < connectionListeners.size(); i++) {
                connectionListeners.get(i).onReadSzlResponse(SZL);
            }
        }
        end(Result);

    }

    /**
     * Read value specified by tag parameter
     * <p>
     * <span color=red>/ ! \ Actualy only data bloc area is processing by this
     * function see below table to do not expect use all other
     * </span>
     * <table border="1">
     * <tr><th>&nbsp;</th><th>Value</th><th>Mean</th></tr>
     * <tr><td>S7Consts.S7AreaPE</td><td>0x81</td><td>Process Inputs.</td></tr>
     * <tr><td>S7Consts.S7AreaPA</td><td>0x82</td><td>Process Outputs.</td></tr>
     * <tr><td>S7Consts.S7AreaMK</td><td>0x83</td><td>Merkers</td></tr>
     * <tr><td>S7Consts.S7AreaDB</td><td>0x84</td><td>DB</td></tr>
     * <tr><td>S7Consts.S7AreaCT</td><td>0x85</td><td>Counters</td></tr>
     * <tr><td>S7Consts.S7AreaTM</td><td>0x86</td><td>Timers</td></tr>
     * </table>
     *
     * <p>
     * The following table display the available table use to adapt siemens to
     * Java
     *
     * <p>
     * <table border="1">
     * <tr><th>N°</th><th>Code</th><th>Name</th><th>Bit</th><th>Byte</th><th>Word</th><th>Brand</th></tr>
     * <tr><td>1</td><td>Bool</td><td>Boolean</td><td>1</td><td>0</td><td>0</td><td>Siemens</td></tr>
     * <tr><td>2</td><td>DateTime</td><td>Date
     * Time</td><td>64</td><td>8</td><td>4</td><td>Siemens</td></tr>
     * <tr><td>3</td><td>DInt</td><td>Double
     * Int</td><td>32</td><td>4</td><td>2</td><td>Siemens</td></tr>
     * <tr><td>4</td><td>Int</td><td>Integer</td><td>16</td><td>2</td><td>1</td><td>Siemens</td></tr>
     * <tr><td>5</td><td>LReal</td><td>Long
     * Read</td><td>?</td><td>?</td><td>?</td><td>Siemens</td></tr>
     * <tr><td>6</td><td>Real</td><td>Real</td><td>64</td><td>8</td><td>4</td><td>Siemens</td></tr>
     * <tr><td>7</td><td>SInt</td><td>Small
     * Int</td><td>8</td><td>1</td><td>0</td><td>Siemens</td></tr>
     * <tr><td>8</td><td>UDInt</td><td>Unsigned Double
     * Integer</td><td>16</td><td>2</td><td>1</td><td>Siemens</td></tr>
     * <tr><td>9</td><td>UInt</td><td>Unsigned
     * Integer</td><td>0</td><td>0</td><td>0</td><td>Siemens</td></tr>
     * <tr><td>10</td><td>USInt</td><td>Unsigned Small
     * Integer</td><td>0</td><td>0</td><td>0</td><td>Siemens</td></tr>
     * <tr><td>11</td><td>Wide
     * String</td><td>0</td><td>0</td><td>0</td><td>0</td><td>Siemens</td></tr>
     * </table>
     *
     *
     *
     * <p>
     * <h3>Following is list of moka7 data type :</h3>
     * <ul>
     * <li>BITS
     * <li>WORD (unsigned 16 bit integer
     * <li>INT (signed 16 bit integer)
     * <li>DWORD (unsigned 32 bit integer
     * <li>DINT (signed 32 bit integer) <br /> This will fetch a 32 bits signed
     * value : from 0 to 4294967295 (2^32-1). {@link S7#GetDIntAt(byte[], int)}
     * which will read 4 bytes from pos.
     * <li>REAL (32 bit floating point number
     * <li>S7 Strings
     * <li>S7 Array of char
     * </ul>
     *
     *
     * <p>
     * <h3>Following is list of java data type specification : </h3>
     * <table border="1">
     * <tr><th>Type</th><th>Taille<br/>(octets)</th><th>Valeur
     * minimale</th><th>Valeur maximale</th></tr>
     * <tr><td>byte</td><td>1</td><td>-128<br/>(Byte.MIN_VALUE)</td><td>127
     * (Byte.MAX_VALUE)</td></tr>
     * <tr><td>short</td><td>2</td><td>-32 768 (Short.MIN_VALUE)</td><td>32 767
     * (Short.MAX_VALUE) </td></tr>
     * <tr><td>int</td><td>4</td><td>-2 147 483
     * 648<br/>(Integer.MIN_VALUE)</td><td>2 147 483
     * 647<br/>(Integer.MAX_VALUE)</td></tr>
     * <tr><td>long</td><td>8</td><td>-9 223 372 036 854 775
     * 808<br/>(Long.MIN_VALUE)</td><td>9 223 372 036 854 775
     * 807<br/>(Long.MAX_VALUE)</td></tr>
     * </table>
     *
     *
     * <p>
     * <h3>The same table exist for data type precision : </h3>
     * <table border="1">
     * <tr><th>Type</th><th>Taille (octets )</th><th>Précision (chiffres
     * significatifs)</th><th>Valeur absolue minimale</th><th>Valeur absolue
     * maximale</th></tr>
     * <tr><td>float</td><td>4</td><td>7</td><td>1.40239846E-45
     * (Float.MIN_VALUE)</td><td>3.40282347E38 (Float.MAX_VALUE)</td></tr>
     * <tr><td>double</td><td>8</td><td>15</td><td>4.9406564584124654E-324
     * (Double.MIN_VALUE)</td><td>1.797693134862316E308
     * (Double.MAX_VALUE)</td></tr>
     * </table>
     *
     * @param tag object containing address and type of value to be read
     * @return Object value Double, Integer, Boolean or null in case of error
     */
    public Object readValue(Tags tag) {

        // Check if machine is connected
        if (!connected) {
            Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                    + " : readValue >> Connection not established ! Please doConnect first !");
            return null;
        }

        // Prepare system
        byte[] Buffer; // buffer for data storage
        int Result = -1; // result reading with default error
        Integer word = 0;
        Object obj = null;  // return object

        /**
         * Select type of data to be read before processing
         */
        switch (tag.getType().getId()) {
            case 1: // Boolean Siemens
                Buffer = new byte[1];
                word = 1;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer);

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Boolean tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                boolean bv = S7.GetBitAt(Buffer, 0, tag.getBit());
                tag.setVBool(bv);
                obj = bv;
                break;
            case 2: // Date Time

                break;
            case 3: // Double Int
                Buffer = new byte[4];
                word = 2;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer);

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Double Integer tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                Integer dv = S7.GetDIntAt(Buffer, 0);
                tag.setVInt(dv);
                tag.setVFloat(dv.doubleValue());
                obj = dv;
                break;
            case 4: // Integer
                Buffer = new byte[2];
                word = 1;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer);

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Integer tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                Integer v = S7.GetShortAt(Buffer, 0);
                tag.setVInt(v);
                tag.setVFloat(v.doubleValue());
                obj = v;
                break;
            case 5: // Long Real

                break;
            case 6: // Real
                Buffer = new byte[8]; // Number of byte
                word = 4;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer); // nombre de mot

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Real tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                Float f = S7.GetFloatAt(Buffer, 0);
                tag.setVInt(f.intValue());
                tag.setVFloat(f.doubleValue());
                obj = f;

                break;
            case 7: // Small Int

                break;
            case 8: // Unsigned Double Integer
                Buffer = new byte[4]; // Number of byte
                word = 2;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer); // nombre de mot

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Unsigned double Integer tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                        + " : Usigned Double Integer never tested convertion to DWord may give overload limit of integer !");
                long udi = S7.GetWordAt(Buffer, 0);
                tag.setVInt((int) udi);
                tag.setVFloat((double) udi);
                obj = udi;
                break;
            case 9: // Unsigned Integer
                Buffer = new byte[2]; // Number of byte
                word = 1;

                // Process reading
                Result = client.ReadArea(S7.S7AreaDB, tag.getDb(),
                        tag.getByte1(),
                        word, Buffer); // nombre de mot

                // Process reading
                if (Result != 0) {
                    Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                            + " : readValue >> Unsigned Integer tag id : "
                            + tag.getId() + " >> value = " + tag.getName()
                            + " bad request on reading DB " + tag.getDb()
                            + " address " + tag.getByte1() + " bit " + tag.getBit());
                    return null;
                }

                // Reading succed process conversion and storage
                int ui = S7.GetWordAt(Buffer, 0);
                tag.setVInt(ui);
                tag.setVFloat((double) ui);
                obj = ui;
                break;
            case 10: // Unsigned Small Integer => DBW

                break;
            case 11: // Wide string

                break;
            default:
                Util.out(Util.errLine() + MachineConnection.class.getSimpleName()
                        + " : readValue(tag) >> undefine type id : " + tag.getType().getId());
                return null;
        }

        return obj;
    }

    /**
     * Identifie if PLC has already been connected. /!\ This one does not
     * identify if connection was lost !
     *
     * @return true if connection has already been connected
     */
    public Boolean getConnected() {
        return client.Connected;
    }

    /**
     * Request a connection to the system. This allow to process connection
     * mecanism
     */
    public void connect() {
        requestConnection = true;
    }

    /**
     * Request a connection state to the system. This allow to process
     * connection mecanism
     */
    public void connectState() {
        requestConnectionState = true;
    }

    /**
     * Request to read date time of CPU
     */
    public void dateTime() {
        requestDateTime = true;
    }

    /**
     * Request to read order code
     */
    public void orderCode() {
        requestOrderCode = true;
    }

    /**
     * Request to read order code
     */
    public void plcStatus() {
        requestPLCStatus = true;
    }

    /**
     * Request to read CPU INFO
     */
    public void plcInfo() {
        requestCpuInfo = true;
    }

    /**
     * Request to read CP INFO
     */
    public void cpInfo() {
        requestCpInfo = true;
    }

    /**
     * Request to read Szl info
     */
    public void SzlInfo() {
        requestSzl = true;
    }

    /**
     * Allow to terminate main loop of thead run
     */
    public void doStop() {
        requestStop = true;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        String methodName = getClass().getSimpleName() + " : run() >> ";
        int loop = 0;
        while (!requestStop) {
            running = true;
            if (requestConnection) {
                doConnect();
                requestConnection = false;
            } else if (requestConnectionState) {
                doConnectState();
                requestConnectionState = false;
            }
            if (requestDateTime && connected) {
                doGetDateAndTime();
                requestDateTime = false;
            }
            if (requestOrderCode && connected) {
                doGetOrderCode();
                requestOrderCode = false;
            }
            if (requestPLCStatus && connected) {
                doGetPLCStatus();
                requestPLCStatus = false;
            }
            if (requestCpuInfo && connected) {
                doGetCpuInfo();
                requestCpuInfo = false;
            }
            if (requestCpInfo && connected) {
                doGetCpInfo();
                requestCpInfo = false;
            }
            if (requestSzl && connected) {
                doReadSzl();
                requestSzl = false;
            }
        }
        running = false;
        requestStop = false;

    }

    public static void HexDump(byte[] Buffer, int Size) {
        int r = 0;
        String Hex = "";

        for (int i = 0; i < Size; i++) {
            int v = (Buffer[i] & 0x0FF);
            String hv = Integer.toHexString(v);

            if (hv.length() == 1) {
                hv = "0" + hv + " ";
            } else {
                hv = hv + " ";
            }

            Hex = Hex + hv;

            r++;
            if (r == 16) {
                System.out.print(Hex + " ");
                System.out.println(S7.GetPrintableStringAt(Buffer, i - 15, 16));
                Hex = "";
                r = 0;
            }
        }
        int L = Hex.length();
        if (L > 0) {
            while (Hex.length() < 49) {
                Hex = Hex + " ";
            }
            System.out.print(Hex);
            System.out.println(S7.GetPrintableStringAt(Buffer, Size - r, r));
        } else {
            System.out.println();
        }
    }

    public Machines getMachine() {
        return machine;
    }

    public void setMachine(Machines machine) {
        this.machine = machine;
    }

    /**
     * Allow to stop main loop if running and close S7 client connection
     */
    void close() {
        requestStop = true;
        client.Disconnect();
    }

    @Override
    public void onNewError(int errorCode, String err) {
        
    }

    @Override
    public void onConnectionSucced(Machines machine, Integer errorCode, String err) {
        
    }

    @Override
    public void onConnectionError(Machines machine, Integer errorCode, String err) {
        
    }

    @Override
    public void onPDUUpdate(Integer PDUNegotiationByte) {
        
    }

    @Override
    public void onDateTimeResponse(Date plcDateTime) {
        
    }

    @Override
    public void isProcessing() {
        
    }

    @Override
    public void onOrderCodeResponse(S7OrderCode orderCode) {
        
    }

    @Override
    public void onPLCStatusResponse(IntByRef status) {
        
    }

    @Override
    public void onPLCInfoResponse(S7CpuInfo CpuInfo) {
        
    }

    @Override
    public void onCpInfoResponse(S7CpInfo CpInfo) {
        
    }

    @Override
    public void onReadSzlResponse(S7Szl SZL) {
        
    }

}
