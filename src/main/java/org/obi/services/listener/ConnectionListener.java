/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.obi.services.listener;

import java.util.Date;
import org.obi.services.core.moka7.IntByRef;
import org.obi.services.core.moka7.S7CpInfo;
import org.obi.services.core.moka7.S7CpuInfo;
import org.obi.services.core.moka7.S7OrderCode;
import org.obi.services.core.moka7.S7Szl;
import org.obi.services.entities.machines.Machines;

/**
 *
 * @author r.hendrick
 */
public interface ConnectionListener {

// EVENT
    void onNewError(int errorCode, String err);

    void onConnectionSucced(Integer duration);

    void onConnectionError(Integer duration);

    void onPDUUpdate(Integer PDUNegotiationByte);

    void onDateTimeResponse(Date plcDateTime);

    void isProcessing();

    void onOrderCodeResponse(S7OrderCode orderCode);

    void onPLCStatusResponse(IntByRef status);

    void onPLCInfoResponse(S7CpuInfo CpuInfo);

    void onCpInfoResponse(S7CpInfo CpInfo);

    void onReadSzlResponse(S7Szl SZL);

    void onConnectionSucced(Machines machine);
}
