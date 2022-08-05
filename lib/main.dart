import 'package:event_channel_practice/image_stream_widget.dart';
import 'package:flutter/material.dart';
import 'network_stream_widget.dart';

void main() {
  runApp(_MyApp());
}

class _MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.brown,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const _MyHomePage(title: 'Events Channel'),
    );
  }
}

class _MyHomePage extends StatelessWidget {
  final String title;

  const _MyHomePage({Key? key, required this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
        elevation: 0.0,
        centerTitle: true,
      ),
      body: Column(
        children: const [
          NetworkStreamWidget(),
          Expanded(child: ImageStreamWidget()),
        ],
      ),
    );
  }
}
