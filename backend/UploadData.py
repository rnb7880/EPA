import paramiko
import os
import time
"""
Test of paramiko's capabilities. Uploads a local file to a
remote server via sftp

@author  - Michael Baumgarten
@version - 2/2/17
"""
def Upload():
    os.system('clear')
    print("    __________  ___")
    print("   / ____/ __ \/   |")
    print("  / __/ / /_/ / /| |")
    print(" / /___/ ____/ ___ |")
    print("/_____/_/   /_/  |_|")
    print("")
    print("System time: "+str(time.strftime("%H:%M:%S")))
    print("")
    # host address and port number
    host = "banjo.rit.edu"
    port = 22

    # paramiko SFTP transport object
    transport = paramiko.Transport((host,port))

    # login credentials

    with open("/home/michael/Documents/notes/nimp.txt") as f:
        content = f.readline().strip()

    password = content
    username = "mb2732"

    # establish connection to remote server
    transport.connect(username=username,password=password)
    print("user: "+username+" establishing connection to "+host+"...")
    time.sleep(.5)
    print("connected!")

    # initiate SFTP
    sftp = paramiko.SFTPClient.from_transport(transport)
    print("initiating SFTP...")
    time.sleep(.5)

    # remote directory upload path
    remotepath0 = "www/epa/lotdata.txt"
    remotepath1 = "www/epa/building_info.txt"

    # local directory file path
    localpath0 = "lotdata.txt"
    localpath1 = "building_info.txt"

    # upload file from local dir to remote dir
    print("attempting transfer "+localpath0+ " to remote directory "+remotepath0+"...")
    time.sleep(1)
    sftp.put(localpath0,remotepath0)
    sftp.put(localpath1,remotepath1)
    print("transfer successful!")
    time.sleep(.5)

    # close out connection
    print("closing connection...")
    time.sleep(.5)
    sftp.close()
    transport.close()
    print("connection closed")
    time.sleep(.5)
    print("")
