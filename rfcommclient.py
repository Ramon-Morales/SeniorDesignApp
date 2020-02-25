import bluetooth

bd_addr = "F0:03:8C:DD:19:BA"

port = 1

sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )
sock.connect((bd_addr, port))

print ("Sending \"hello!!\"")
sock.send("hello!!")

sock.close()
