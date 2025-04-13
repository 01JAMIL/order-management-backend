package com.ordermanagement.backend.common;

public enum MachineStatus {
    OPERATIONAL,        // Machine is running fine
    UNDER_MAINTENANCE , // Machine is under maintenance
    OUT_OF_ORDER ,      // Machine is broken / not working
    IDLE ,              // Waiting for task / not currently working
    DECOMMISSIONED      // Permanently out of service
}
