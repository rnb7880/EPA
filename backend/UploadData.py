import paramiko
import base64
import os
"""
Test of paramiko's capabilities. Uploads a local file to a
remote server via sftp

@author  - Michael Baumgarten
@version - 2/2/17
"""
def Upload():
    os.system('clear')
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
    print("user: "+username+" establishing connection to "+host)

    # initiate SFTP
    sftp = paramiko.SFTPClient.from_transport(transport)
    print("initiating SFTP")

    # remote directory upload path
    remotepath = "www/epa/lotdata.txt"

    # local directory file path
    localpath = "lotdata.txt"

    # upload file from local dir to remote dir
    print("attempting transfer "+localpath+ " to remote directory "+remotepath)
    sftp.put(localpath,remotepath)
    print("transfer successful!")

    # close out connection
    print("closing connection")
    sftp.close()
    transport.close()
    print("connection closed")
