import 'package:event_channel_practice/constants.dart';
import 'package:flutter/material.dart';

enum Connection{
  wifi,
  internet,
  disconnected,
  unknown,
}

Connection intToConnection(int connectionInt){
  var connection = Connection.unknown;
  switch(connectionInt){
    case Constants.wifi:
      connection = Connection.wifi;
      break;
    case Constants.internet:
      connection = Connection.internet;
      break;
    case Constants.disconnected:
      connection = Connection.disconnected;
      break;
    case Constants.unknown:
      connection = Connection.unknown;
      break;
  }
  return connection;
}

/// converts the network events to the appropriate Color
Color getConnectionColor(Connection connection) {
  var color = Colors.red[900];
  switch (connection) {
    case Connection.wifi:
      color = Colors.green[800];
      break;
    case Connection.internet:
      color = Colors.blue[900];
      break;
    case Connection.disconnected:
      color = Colors.red[900];
      break;
    case Connection.unknown:
      color = Colors.grey[900];
      break;
  }
  return color!;
}

/// converts the network events to the appropriate user-readable strings
String getConnectionMessage(Connection connection) {
  var msg = 'Unknown connection';
  switch (connection) {
    case Connection.wifi:
      msg = 'Connected to Wifi';
      break;
    case Connection.internet:
      msg = 'Connected to Mobile data';
      break;
    case Connection.disconnected:
      msg = 'Offline';
      break;
    case Connection.unknown:
      msg = 'Unknown connection';
      break;
  }
  return msg;
}
