package android.car.hardware.XpVehicle;

import android.car.diagnostic.EcusFailureStates;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IXpDiagnostic extends IInterface {
    int getAebState() throws RemoteException;

    int getAgsFailureState() throws RemoteException;

    int getAirbagFaultSt() throws RemoteException;

    EcusFailureStates getAllEcusFailureStates() throws RemoteException;

    int[] getAllMsmModulesFailureStates() throws RemoteException;

    int[] getAllTirePerssureSensorsFailureStates() throws RemoteException;

    int[] getAllTireTemperatureWarnings() throws RemoteException;

    int getAlsErrorSt() throws RemoteException;

    byte[] getAndLastDiagCmd() throws RemoteException;

    int getAtlsErrSt() throws RemoteException;

    int getAvasFault() throws RemoteException;

    int getBatteryOverheatingState() throws RemoteException;

    int[] getBatteryPtcFailureStates() throws RemoteException;

    int getBatteryVoltageLowState() throws RemoteException;

    int getCcsWorkState() throws RemoteException;

    int getCdcDataUploadSt() throws RemoteException;

    int getCiuSdcardFailureState() throws RemoteException;

    int getDcdcFailureState() throws RemoteException;

    int getDhcErr() throws RemoteException;

    int getDvrFailureState() throws RemoteException;

    int getElectricMotorSystemHotState() throws RemoteException;

    int getElectricVacuumPumpFailureState() throws RemoteException;

    int getEpbSysFailureState() throws RemoteException;

    int getEpsWarnLamp() throws RemoteException;

    int getEspAbsFault() throws RemoteException;

    int getEspApbErrSt() throws RemoteException;

    int getEspAvhFault() throws RemoteException;

    int getEspEspFault() throws RemoteException;

    int getFrontIpuFailureState() throws RemoteException;

    int getHdcFailureState() throws RemoteException;

    int getHighBeamFailState() throws RemoteException;

    int getHighVoltageRelayAdhesionState() throws RemoteException;

    int[] getHvacCompressorFailureStates() throws RemoteException;

    int getHvacPtcFailureState() throws RemoteException;

    int getIbtFailureLamp() throws RemoteException;

    int getLDtrOutputFailState() throws RemoteException;

    int getLTurnLampFailState() throws RemoteException;

    int getLdwState() throws RemoteException;

    int getLluErrSt() throws RemoteException;

    int getLowBeamFailState() throws RemoteException;

    byte[] getMaterSlaveSettings() throws RemoteException;

    byte[] getMcuLastDiagCmd() throws RemoteException;

    int getMsmdEcuErr() throws RemoteException;

    int getMsmdHeatSysErr() throws RemoteException;

    int getMsmdVentilationMotorErr() throws RemoteException;

    int getMsmpEcuErr() throws RemoteException;

    int getMsmpHeatSysErr() throws RemoteException;

    int getParkingLampFailState() throws RemoteException;

    int getRDtrOutputFailState() throws RemoteException;

    int getRTurnLampFailState() throws RemoteException;

    int getRearFogFailState() throws RemoteException;

    int getRearIpuFailureState() throws RemoteException;

    int getScuAlcCtrlRemind() throws RemoteException;

    int getScuBsdSw() throws RemoteException;

    int getScuDowSw() throws RemoteException;

    int getScuIcmApaErrTips() throws RemoteException;

    int getScuIhbSw() throws RemoteException;

    int getScuIslcSw() throws RemoteException;

    int getScuLongCtrlRemind() throws RemoteException;

    int getScuRctaSw() throws RemoteException;

    int getScuRcwSw() throws RemoteException;

    int getSideReversingState() throws RemoteException;

    int getSrrFlFailState() throws RemoteException;

    int getSrrFrFailState() throws RemoteException;

    int getSrrRlFailState() throws RemoteException;

    int getSrrRrFailState() throws RemoteException;

    int getSysErrorWarn() throws RemoteException;

    int getSystemDiagContent() throws RemoteException;

    int[] getTcmMotorFailureStates() throws RemoteException;

    int getTpmsAbnormalPrWarn() throws RemoteException;

    int getTpmsSysFaultWarn() throws RemoteException;

    int getVcuBCruiseErr() throws RemoteException;

    int getVcuChgPortHotDsp() throws RemoteException;

    int getVcuDeadBatteryFlag() throws RemoteException;

    int getVcuEbsErrDsp() throws RemoteException;

    int getVcuEvErrLampDsp() throws RemoteException;

    int getVcuEvErrorMessage() throws RemoteException;

    int getVcuGearWarning() throws RemoteException;

    int getVcuLiquidHighTempErr() throws RemoteException;

    int getVcuObcMsgLost() throws RemoteException;

    int getVcuPowerLimitationDsp() throws RemoteException;

    int getVcuThermoRunawaySt() throws RemoteException;

    int getVcuWaterSensorErr() throws RemoteException;

    int getXpuXpuFailState() throws RemoteException;

    void registerListener(int i, float f, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    void sendAndDiagCmd(byte[] bArr) throws RemoteException;

    void sendCableDiagToMcu(byte[] bArr) throws RemoteException;

    void sendInsufficientSQI(byte[] bArr) throws RemoteException;

    void sendIpErr(byte[] bArr) throws RemoteException;

    void sendLinkStatus(byte[] bArr) throws RemoteException;

    void sendMaterSlaveMode(byte[] bArr) throws RemoteException;

    void sendMaterSlaveSettingsResponse(byte[] bArr) throws RemoteException;

    void sendMcuDiagCmdAck(byte[] bArr) throws RemoteException;

    void sendReceivedPak(byte[] bArr) throws RemoteException;

    void sendResetPhyToMcu(byte[] bArr) throws RemoteException;

    void sendSQI1000Base(byte[] bArr) throws RemoteException;

    void sendSQI100Base(byte[] bArr) throws RemoteException;

    void sendSQIMax(byte[] bArr) throws RemoteException;

    void sendSQIValue(byte[] bArr) throws RemoteException;

    void sendTestModeToMcu(byte[] bArr) throws RemoteException;

    void sendTransmittedPak(byte[] bArr) throws RemoteException;

    void sendUnexpectedLnkLoss(byte[] bArr) throws RemoteException;

    void setCarServiceDebugEnabled(boolean z) throws RemoteException;

    void unregisterListener(int i, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IXpDiagnostic {
        private static final String DESCRIPTOR = "android.car.hardware.XpVehicle.IXpDiagnostic";
        static final int TRANSACTION_getAebState = 100;
        static final int TRANSACTION_getAgsFailureState = 94;
        static final int TRANSACTION_getAirbagFaultSt = 44;
        static final int TRANSACTION_getAllEcusFailureStates = 79;
        static final int TRANSACTION_getAllMsmModulesFailureStates = 96;
        static final int TRANSACTION_getAllTirePerssureSensorsFailureStates = 86;
        static final int TRANSACTION_getAllTireTemperatureWarnings = 85;
        static final int TRANSACTION_getAlsErrorSt = 45;
        static final int TRANSACTION_getAndLastDiagCmd = 5;
        static final int TRANSACTION_getAtlsErrSt = 35;
        static final int TRANSACTION_getAvasFault = 41;
        static final int TRANSACTION_getBatteryOverheatingState = 87;
        static final int TRANSACTION_getBatteryPtcFailureStates = 104;
        static final int TRANSACTION_getBatteryVoltageLowState = 90;
        static final int TRANSACTION_getCcsWorkState = 88;
        static final int TRANSACTION_getCdcDataUploadSt = 64;
        static final int TRANSACTION_getCiuSdcardFailureState = 98;
        static final int TRANSACTION_getDcdcFailureState = 89;
        static final int TRANSACTION_getDhcErr = 46;
        static final int TRANSACTION_getDvrFailureState = 97;
        static final int TRANSACTION_getElectricMotorSystemHotState = 91;
        static final int TRANSACTION_getElectricVacuumPumpFailureState = 92;
        static final int TRANSACTION_getEpbSysFailureState = 83;
        static final int TRANSACTION_getEpsWarnLamp = 63;
        static final int TRANSACTION_getEspAbsFault = 60;
        static final int TRANSACTION_getEspApbErrSt = 59;
        static final int TRANSACTION_getEspAvhFault = 61;
        static final int TRANSACTION_getEspEspFault = 58;
        static final int TRANSACTION_getFrontIpuFailureState = 81;
        static final int TRANSACTION_getHdcFailureState = 84;
        static final int TRANSACTION_getHighBeamFailState = 25;
        static final int TRANSACTION_getHighVoltageRelayAdhesionState = 93;
        static final int TRANSACTION_getHvacCompressorFailureStates = 103;
        static final int TRANSACTION_getHvacPtcFailureState = 102;
        static final int TRANSACTION_getIbtFailureLamp = 62;
        static final int TRANSACTION_getLDtrOutputFailState = 31;
        static final int TRANSACTION_getLTurnLampFailState = 27;
        static final int TRANSACTION_getLdwState = 99;
        static final int TRANSACTION_getLluErrSt = 34;
        static final int TRANSACTION_getLowBeamFailState = 26;
        static final int TRANSACTION_getMaterSlaveSettings = 23;
        static final int TRANSACTION_getMcuLastDiagCmd = 3;
        static final int TRANSACTION_getMsmdEcuErr = 36;
        static final int TRANSACTION_getMsmdHeatSysErr = 38;
        static final int TRANSACTION_getMsmdVentilationMotorErr = 37;
        static final int TRANSACTION_getMsmpEcuErr = 39;
        static final int TRANSACTION_getMsmpHeatSysErr = 40;
        static final int TRANSACTION_getParkingLampFailState = 32;
        static final int TRANSACTION_getRDtrOutputFailState = 30;
        static final int TRANSACTION_getRTurnLampFailState = 28;
        static final int TRANSACTION_getRearFogFailState = 29;
        static final int TRANSACTION_getRearIpuFailureState = 82;
        static final int TRANSACTION_getScuAlcCtrlRemind = 77;
        static final int TRANSACTION_getScuBsdSw = 67;
        static final int TRANSACTION_getScuDowSw = 68;
        static final int TRANSACTION_getScuIcmApaErrTips = 78;
        static final int TRANSACTION_getScuIhbSw = 66;
        static final int TRANSACTION_getScuIslcSw = 76;
        static final int TRANSACTION_getScuLongCtrlRemind = 75;
        static final int TRANSACTION_getScuRctaSw = 69;
        static final int TRANSACTION_getScuRcwSw = 70;
        static final int TRANSACTION_getSideReversingState = 101;
        static final int TRANSACTION_getSrrFlFailState = 71;
        static final int TRANSACTION_getSrrFrFailState = 72;
        static final int TRANSACTION_getSrrRlFailState = 73;
        static final int TRANSACTION_getSrrRrFailState = 74;
        static final int TRANSACTION_getSysErrorWarn = 33;
        static final int TRANSACTION_getSystemDiagContent = 7;
        static final int TRANSACTION_getTcmMotorFailureStates = 95;
        static final int TRANSACTION_getTpmsAbnormalPrWarn = 43;
        static final int TRANSACTION_getTpmsSysFaultWarn = 42;
        static final int TRANSACTION_getVcuBCruiseErr = 52;
        static final int TRANSACTION_getVcuChgPortHotDsp = 55;
        static final int TRANSACTION_getVcuDeadBatteryFlag = 57;
        static final int TRANSACTION_getVcuEbsErrDsp = 49;
        static final int TRANSACTION_getVcuEvErrLampDsp = 47;
        static final int TRANSACTION_getVcuEvErrorMessage = 80;
        static final int TRANSACTION_getVcuGearWarning = 48;
        static final int TRANSACTION_getVcuLiquidHighTempErr = 50;
        static final int TRANSACTION_getVcuObcMsgLost = 56;
        static final int TRANSACTION_getVcuPowerLimitationDsp = 54;
        static final int TRANSACTION_getVcuThermoRunawaySt = 53;
        static final int TRANSACTION_getVcuWaterSensorErr = 51;
        static final int TRANSACTION_getXpuXpuFailState = 65;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_sendAndDiagCmd = 6;
        static final int TRANSACTION_sendCableDiagToMcu = 11;
        static final int TRANSACTION_sendInsufficientSQI = 17;
        static final int TRANSACTION_sendIpErr = 18;
        static final int TRANSACTION_sendLinkStatus = 12;
        static final int TRANSACTION_sendMaterSlaveMode = 22;
        static final int TRANSACTION_sendMaterSlaveSettingsResponse = 24;
        static final int TRANSACTION_sendMcuDiagCmdAck = 4;
        static final int TRANSACTION_sendReceivedPak = 15;
        static final int TRANSACTION_sendResetPhyToMcu = 9;
        static final int TRANSACTION_sendSQI1000Base = 21;
        static final int TRANSACTION_sendSQI100Base = 20;
        static final int TRANSACTION_sendSQIMax = 19;
        static final int TRANSACTION_sendSQIValue = 13;
        static final int TRANSACTION_sendTestModeToMcu = 10;
        static final int TRANSACTION_sendTransmittedPak = 14;
        static final int TRANSACTION_sendUnexpectedLnkLoss = 16;
        static final int TRANSACTION_setCarServiceDebugEnabled = 8;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXpDiagnostic asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IXpDiagnostic)) {
                return (IXpDiagnostic) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    float _arg1 = data.readFloat();
                    ICarPropertyEventListener _arg2 = ICarPropertyEventListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    ICarPropertyEventListener _arg12 = ICarPropertyEventListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result = getMcuLastDiagCmd();
                    reply.writeNoException();
                    reply.writeByteArray(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg03 = data.createByteArray();
                    sendMcuDiagCmdAck(_arg03);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result2 = getAndLastDiagCmd();
                    reply.writeNoException();
                    reply.writeByteArray(_result2);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg04 = data.createByteArray();
                    sendAndDiagCmd(_arg04);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getSystemDiagContent();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg05 = data.readInt() != 0;
                    setCarServiceDebugEnabled(_arg05);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg06 = data.createByteArray();
                    sendResetPhyToMcu(_arg06);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg07 = data.createByteArray();
                    sendTestModeToMcu(_arg07);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg08 = data.createByteArray();
                    sendCableDiagToMcu(_arg08);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg09 = data.createByteArray();
                    sendLinkStatus(_arg09);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg010 = data.createByteArray();
                    sendSQIValue(_arg010);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg011 = data.createByteArray();
                    sendTransmittedPak(_arg011);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg012 = data.createByteArray();
                    sendReceivedPak(_arg012);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg013 = data.createByteArray();
                    sendUnexpectedLnkLoss(_arg013);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg014 = data.createByteArray();
                    sendInsufficientSQI(_arg014);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg015 = data.createByteArray();
                    sendIpErr(_arg015);
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg016 = data.createByteArray();
                    sendSQIMax(_arg016);
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg017 = data.createByteArray();
                    sendSQI100Base(_arg017);
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg018 = data.createByteArray();
                    sendSQI1000Base(_arg018);
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg019 = data.createByteArray();
                    sendMaterSlaveMode(_arg019);
                    reply.writeNoException();
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result4 = getMaterSlaveSettings();
                    reply.writeNoException();
                    reply.writeByteArray(_result4);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg020 = data.createByteArray();
                    sendMaterSlaveSettingsResponse(_arg020);
                    reply.writeNoException();
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = getHighBeamFailState();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getLowBeamFailState();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getLTurnLampFailState();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = getRTurnLampFailState();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getRearFogFailState();
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getRDtrOutputFailState();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = getLDtrOutputFailState();
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = getParkingLampFailState();
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = getSysErrorWarn();
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    int _result14 = getLluErrSt();
                    reply.writeNoException();
                    reply.writeInt(_result14);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    int _result15 = getAtlsErrSt();
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    int _result16 = getMsmdEcuErr();
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = getMsmdVentilationMotorErr();
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    int _result18 = getMsmdHeatSysErr();
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    int _result19 = getMsmpEcuErr();
                    reply.writeNoException();
                    reply.writeInt(_result19);
                    return true;
                case 40:
                    data.enforceInterface(DESCRIPTOR);
                    int _result20 = getMsmpHeatSysErr();
                    reply.writeNoException();
                    reply.writeInt(_result20);
                    return true;
                case 41:
                    data.enforceInterface(DESCRIPTOR);
                    int _result21 = getAvasFault();
                    reply.writeNoException();
                    reply.writeInt(_result21);
                    return true;
                case 42:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = getTpmsSysFaultWarn();
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case 43:
                    data.enforceInterface(DESCRIPTOR);
                    int _result23 = getTpmsAbnormalPrWarn();
                    reply.writeNoException();
                    reply.writeInt(_result23);
                    return true;
                case 44:
                    data.enforceInterface(DESCRIPTOR);
                    int _result24 = getAirbagFaultSt();
                    reply.writeNoException();
                    reply.writeInt(_result24);
                    return true;
                case 45:
                    data.enforceInterface(DESCRIPTOR);
                    int _result25 = getAlsErrorSt();
                    reply.writeNoException();
                    reply.writeInt(_result25);
                    return true;
                case 46:
                    data.enforceInterface(DESCRIPTOR);
                    int _result26 = getDhcErr();
                    reply.writeNoException();
                    reply.writeInt(_result26);
                    return true;
                case 47:
                    data.enforceInterface(DESCRIPTOR);
                    int _result27 = getVcuEvErrLampDsp();
                    reply.writeNoException();
                    reply.writeInt(_result27);
                    return true;
                case 48:
                    data.enforceInterface(DESCRIPTOR);
                    int _result28 = getVcuGearWarning();
                    reply.writeNoException();
                    reply.writeInt(_result28);
                    return true;
                case 49:
                    data.enforceInterface(DESCRIPTOR);
                    int _result29 = getVcuEbsErrDsp();
                    reply.writeNoException();
                    reply.writeInt(_result29);
                    return true;
                case 50:
                    data.enforceInterface(DESCRIPTOR);
                    int _result30 = getVcuLiquidHighTempErr();
                    reply.writeNoException();
                    reply.writeInt(_result30);
                    return true;
                case 51:
                    data.enforceInterface(DESCRIPTOR);
                    int _result31 = getVcuWaterSensorErr();
                    reply.writeNoException();
                    reply.writeInt(_result31);
                    return true;
                case 52:
                    data.enforceInterface(DESCRIPTOR);
                    int _result32 = getVcuBCruiseErr();
                    reply.writeNoException();
                    reply.writeInt(_result32);
                    return true;
                case 53:
                    data.enforceInterface(DESCRIPTOR);
                    int _result33 = getVcuThermoRunawaySt();
                    reply.writeNoException();
                    reply.writeInt(_result33);
                    return true;
                case 54:
                    data.enforceInterface(DESCRIPTOR);
                    int _result34 = getVcuPowerLimitationDsp();
                    reply.writeNoException();
                    reply.writeInt(_result34);
                    return true;
                case 55:
                    data.enforceInterface(DESCRIPTOR);
                    int _result35 = getVcuChgPortHotDsp();
                    reply.writeNoException();
                    reply.writeInt(_result35);
                    return true;
                case 56:
                    data.enforceInterface(DESCRIPTOR);
                    int _result36 = getVcuObcMsgLost();
                    reply.writeNoException();
                    reply.writeInt(_result36);
                    return true;
                case 57:
                    data.enforceInterface(DESCRIPTOR);
                    int _result37 = getVcuDeadBatteryFlag();
                    reply.writeNoException();
                    reply.writeInt(_result37);
                    return true;
                case 58:
                    data.enforceInterface(DESCRIPTOR);
                    int _result38 = getEspEspFault();
                    reply.writeNoException();
                    reply.writeInt(_result38);
                    return true;
                case 59:
                    data.enforceInterface(DESCRIPTOR);
                    int _result39 = getEspApbErrSt();
                    reply.writeNoException();
                    reply.writeInt(_result39);
                    return true;
                case 60:
                    data.enforceInterface(DESCRIPTOR);
                    int _result40 = getEspAbsFault();
                    reply.writeNoException();
                    reply.writeInt(_result40);
                    return true;
                case 61:
                    data.enforceInterface(DESCRIPTOR);
                    int _result41 = getEspAvhFault();
                    reply.writeNoException();
                    reply.writeInt(_result41);
                    return true;
                case 62:
                    data.enforceInterface(DESCRIPTOR);
                    int _result42 = getIbtFailureLamp();
                    reply.writeNoException();
                    reply.writeInt(_result42);
                    return true;
                case 63:
                    data.enforceInterface(DESCRIPTOR);
                    int _result43 = getEpsWarnLamp();
                    reply.writeNoException();
                    reply.writeInt(_result43);
                    return true;
                case 64:
                    data.enforceInterface(DESCRIPTOR);
                    int _result44 = getCdcDataUploadSt();
                    reply.writeNoException();
                    reply.writeInt(_result44);
                    return true;
                case 65:
                    data.enforceInterface(DESCRIPTOR);
                    int _result45 = getXpuXpuFailState();
                    reply.writeNoException();
                    reply.writeInt(_result45);
                    return true;
                case 66:
                    data.enforceInterface(DESCRIPTOR);
                    int _result46 = getScuIhbSw();
                    reply.writeNoException();
                    reply.writeInt(_result46);
                    return true;
                case 67:
                    data.enforceInterface(DESCRIPTOR);
                    int _result47 = getScuBsdSw();
                    reply.writeNoException();
                    reply.writeInt(_result47);
                    return true;
                case 68:
                    data.enforceInterface(DESCRIPTOR);
                    int _result48 = getScuDowSw();
                    reply.writeNoException();
                    reply.writeInt(_result48);
                    return true;
                case 69:
                    data.enforceInterface(DESCRIPTOR);
                    int _result49 = getScuRctaSw();
                    reply.writeNoException();
                    reply.writeInt(_result49);
                    return true;
                case 70:
                    data.enforceInterface(DESCRIPTOR);
                    int _result50 = getScuRcwSw();
                    reply.writeNoException();
                    reply.writeInt(_result50);
                    return true;
                case 71:
                    data.enforceInterface(DESCRIPTOR);
                    int _result51 = getSrrFlFailState();
                    reply.writeNoException();
                    reply.writeInt(_result51);
                    return true;
                case TRANSACTION_getSrrFrFailState /* 72 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result52 = getSrrFrFailState();
                    reply.writeNoException();
                    reply.writeInt(_result52);
                    return true;
                case TRANSACTION_getSrrRlFailState /* 73 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result53 = getSrrRlFailState();
                    reply.writeNoException();
                    reply.writeInt(_result53);
                    return true;
                case TRANSACTION_getSrrRrFailState /* 74 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result54 = getSrrRrFailState();
                    reply.writeNoException();
                    reply.writeInt(_result54);
                    return true;
                case TRANSACTION_getScuLongCtrlRemind /* 75 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result55 = getScuLongCtrlRemind();
                    reply.writeNoException();
                    reply.writeInt(_result55);
                    return true;
                case TRANSACTION_getScuIslcSw /* 76 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result56 = getScuIslcSw();
                    reply.writeNoException();
                    reply.writeInt(_result56);
                    return true;
                case TRANSACTION_getScuAlcCtrlRemind /* 77 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result57 = getScuAlcCtrlRemind();
                    reply.writeNoException();
                    reply.writeInt(_result57);
                    return true;
                case TRANSACTION_getScuIcmApaErrTips /* 78 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result58 = getScuIcmApaErrTips();
                    reply.writeNoException();
                    reply.writeInt(_result58);
                    return true;
                case TRANSACTION_getAllEcusFailureStates /* 79 */:
                    data.enforceInterface(DESCRIPTOR);
                    EcusFailureStates _result59 = getAllEcusFailureStates();
                    reply.writeNoException();
                    if (_result59 != null) {
                        reply.writeInt(1);
                        _result59.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 80:
                    data.enforceInterface(DESCRIPTOR);
                    int _result60 = getVcuEvErrorMessage();
                    reply.writeNoException();
                    reply.writeInt(_result60);
                    return true;
                case TRANSACTION_getFrontIpuFailureState /* 81 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result61 = getFrontIpuFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result61);
                    return true;
                case TRANSACTION_getRearIpuFailureState /* 82 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result62 = getRearIpuFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result62);
                    return true;
                case 83:
                    data.enforceInterface(DESCRIPTOR);
                    int _result63 = getEpbSysFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result63);
                    return true;
                case TRANSACTION_getHdcFailureState /* 84 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result64 = getHdcFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result64);
                    return true;
                case TRANSACTION_getAllTireTemperatureWarnings /* 85 */:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result65 = getAllTireTemperatureWarnings();
                    reply.writeNoException();
                    reply.writeIntArray(_result65);
                    return true;
                case TRANSACTION_getAllTirePerssureSensorsFailureStates /* 86 */:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result66 = getAllTirePerssureSensorsFailureStates();
                    reply.writeNoException();
                    reply.writeIntArray(_result66);
                    return true;
                case TRANSACTION_getBatteryOverheatingState /* 87 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result67 = getBatteryOverheatingState();
                    reply.writeNoException();
                    reply.writeInt(_result67);
                    return true;
                case TRANSACTION_getCcsWorkState /* 88 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result68 = getCcsWorkState();
                    reply.writeNoException();
                    reply.writeInt(_result68);
                    return true;
                case 89:
                    data.enforceInterface(DESCRIPTOR);
                    int _result69 = getDcdcFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result69);
                    return true;
                case TRANSACTION_getBatteryVoltageLowState /* 90 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result70 = getBatteryVoltageLowState();
                    reply.writeNoException();
                    reply.writeInt(_result70);
                    return true;
                case 91:
                    data.enforceInterface(DESCRIPTOR);
                    int _result71 = getElectricMotorSystemHotState();
                    reply.writeNoException();
                    reply.writeInt(_result71);
                    return true;
                case 92:
                    data.enforceInterface(DESCRIPTOR);
                    int _result72 = getElectricVacuumPumpFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result72);
                    return true;
                case TRANSACTION_getHighVoltageRelayAdhesionState /* 93 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result73 = getHighVoltageRelayAdhesionState();
                    reply.writeNoException();
                    reply.writeInt(_result73);
                    return true;
                case TRANSACTION_getAgsFailureState /* 94 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result74 = getAgsFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result74);
                    return true;
                case TRANSACTION_getTcmMotorFailureStates /* 95 */:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result75 = getTcmMotorFailureStates();
                    reply.writeNoException();
                    reply.writeIntArray(_result75);
                    return true;
                case 96:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result76 = getAllMsmModulesFailureStates();
                    reply.writeNoException();
                    reply.writeIntArray(_result76);
                    return true;
                case 97:
                    data.enforceInterface(DESCRIPTOR);
                    int _result77 = getDvrFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result77);
                    return true;
                case 98:
                    data.enforceInterface(DESCRIPTOR);
                    int _result78 = getCiuSdcardFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result78);
                    return true;
                case 99:
                    data.enforceInterface(DESCRIPTOR);
                    int _result79 = getLdwState();
                    reply.writeNoException();
                    reply.writeInt(_result79);
                    return true;
                case 100:
                    data.enforceInterface(DESCRIPTOR);
                    int _result80 = getAebState();
                    reply.writeNoException();
                    reply.writeInt(_result80);
                    return true;
                case 101:
                    data.enforceInterface(DESCRIPTOR);
                    int _result81 = getSideReversingState();
                    reply.writeNoException();
                    reply.writeInt(_result81);
                    return true;
                case TRANSACTION_getHvacPtcFailureState /* 102 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result82 = getHvacPtcFailureState();
                    reply.writeNoException();
                    reply.writeInt(_result82);
                    return true;
                case TRANSACTION_getHvacCompressorFailureStates /* 103 */:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result83 = getHvacCompressorFailureStates();
                    reply.writeNoException();
                    reply.writeIntArray(_result83);
                    return true;
                case TRANSACTION_getBatteryPtcFailureStates /* 104 */:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result84 = getBatteryPtcFailureStates();
                    reply.writeNoException();
                    reply.writeIntArray(_result84);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IXpDiagnostic {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void registerListener(int propId, float rate, ICarPropertyEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(propId);
                    _data.writeFloat(rate);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void unregisterListener(int propId, ICarPropertyEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(propId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public byte[] getMcuLastDiagCmd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendMcuDiagCmdAck(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public byte[] getAndLastDiagCmd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendAndDiagCmd(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSystemDiagContent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void setCarServiceDebugEnabled(boolean on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(on ? 1 : 0);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendResetPhyToMcu(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendTestModeToMcu(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendCableDiagToMcu(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendLinkStatus(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendSQIValue(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendTransmittedPak(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendReceivedPak(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendUnexpectedLnkLoss(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendInsufficientSQI(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendIpErr(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendSQIMax(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendSQI100Base(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendSQI1000Base(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendMaterSlaveMode(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public byte[] getMaterSlaveSettings() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public void sendMaterSlaveSettingsResponse(byte[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(cmd);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getHighBeamFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getLowBeamFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getLTurnLampFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getRTurnLampFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getRearFogFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getRDtrOutputFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getLDtrOutputFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getParkingLampFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSysErrorWarn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getLluErrSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAtlsErrSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getMsmdEcuErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getMsmdVentilationMotorErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getMsmdHeatSysErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getMsmpEcuErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getMsmpHeatSysErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAvasFault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getTpmsSysFaultWarn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getTpmsAbnormalPrWarn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAirbagFaultSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAlsErrorSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getDhcErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuEvErrLampDsp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuGearWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(48, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuEbsErrDsp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(49, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuLiquidHighTempErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(50, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuWaterSensorErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(51, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuBCruiseErr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(52, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuThermoRunawaySt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(53, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuPowerLimitationDsp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(54, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuChgPortHotDsp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(55, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuObcMsgLost() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(56, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuDeadBatteryFlag() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(57, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEspEspFault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(58, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEspApbErrSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(59, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEspAbsFault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(60, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEspAvhFault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(61, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getIbtFailureLamp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(62, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEpsWarnLamp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(63, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getCdcDataUploadSt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(64, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getXpuXpuFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(65, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuIhbSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(66, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuBsdSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(67, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuDowSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(68, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuRctaSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(69, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuRcwSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(70, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSrrFlFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(71, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSrrFrFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSrrFrFailState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSrrRlFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSrrRlFailState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSrrRrFailState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getSrrRrFailState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuLongCtrlRemind() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getScuLongCtrlRemind, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuIslcSw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getScuIslcSw, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuAlcCtrlRemind() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getScuAlcCtrlRemind, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getScuIcmApaErrTips() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getScuIcmApaErrTips, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public EcusFailureStates getAllEcusFailureStates() throws RemoteException {
                EcusFailureStates _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAllEcusFailureStates, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = EcusFailureStates.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getVcuEvErrorMessage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(80, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getFrontIpuFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getFrontIpuFailureState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getRearIpuFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getRearIpuFailureState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getEpbSysFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(83, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getHdcFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getHdcFailureState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getAllTireTemperatureWarnings() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAllTireTemperatureWarnings, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getAllTirePerssureSensorsFailureStates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAllTirePerssureSensorsFailureStates, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getBatteryOverheatingState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getBatteryOverheatingState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getCcsWorkState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getCcsWorkState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getDcdcFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(89, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getBatteryVoltageLowState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getBatteryVoltageLowState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getElectricMotorSystemHotState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(91, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getElectricVacuumPumpFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(92, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getHighVoltageRelayAdhesionState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getHighVoltageRelayAdhesionState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAgsFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAgsFailureState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getTcmMotorFailureStates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getTcmMotorFailureStates, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getAllMsmModulesFailureStates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(96, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getDvrFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(97, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getCiuSdcardFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(98, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getLdwState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(99, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getAebState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(100, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getSideReversingState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(101, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int getHvacPtcFailureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getHvacPtcFailureState, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getHvacCompressorFailureStates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getHvacCompressorFailureStates, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.XpVehicle.IXpDiagnostic
            public int[] getBatteryPtcFailureStates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getBatteryPtcFailureStates, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
