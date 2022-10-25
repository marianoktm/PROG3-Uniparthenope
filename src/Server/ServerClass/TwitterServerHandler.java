package Server.ServerClass;

import Server.Database.MySQLDatabase;
import Server.Operations.Op0Register;
import Server.Operations.OperationCommand;
import Server.Queries.QueriesAdapter.QueryFetchAdapter;
import Server.Queries.QueryClasses.GetUserDataQuery;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Shared.Packet;
import Shared.PacketHelper;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class TwitterServerHandler extends Thread {
    private final Socket clientSocket;
    private PacketHelper packetHelper;

    public TwitterServerHandler(Socket socket) {
            this.clientSocket = socket;
            packetHelper = new PacketHelper(socket);
    }

    public void run() {
        Packet packet;
        System.out.println("Connection by client: " + clientSocket.getInetAddress());

        packet = getPacket();


    }

    private void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    private Packet getPacket() {
        return packetHelper.getPacket();
    }

    private void operation(Packet packet) {
        OperationCommand op;

        switch (packet.request) {
            case 0: // register
                op = new Op0Register(clientSocket, packet);
                op.execute();

        }
    }
}


