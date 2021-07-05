package org.raghav.tutorials.model;

import javax.annotation.ParametersAreNonnullByDefault;
import com.google.common.base.Preconditions;

@ParametersAreNonnullByDefault
public class Config {
    private String serverHost;
    private int serverPort;
    private String serverScheme;
    private String serverUserName;
    private String serverPassword;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        Preconditions.checkNotNull(serverHost, "serverHost must not be null");
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        Preconditions.checkNotNull(serverPort, "serverPort must not be null");
        this.serverPort = serverPort;
    }

    public String getServerScheme() {
        return serverScheme;
    }

    public void setServerScheme(String serverScheme) {
        Preconditions.checkNotNull(serverScheme, "serverScheme must not be null");
        this.serverScheme = serverScheme;
    }

    public String getServerUserName() {
        return serverUserName;
    }

    public void setServerUserName(String serverUserName) {
        Preconditions.checkNotNull(serverUserName, "serverUserName must not be null");
        this.serverUserName = serverUserName;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        Preconditions.checkNotNull(serverPassword, "serverPassword must not be null");
        this.serverPassword = serverPassword;
    }
}