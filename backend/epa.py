import cv2
import numpy as np
from pkspot import *
from SortCoord import *
import time
import UploadData
import os

def readmask(mask):
    """
    populates the pointlist with all points found in the mask
    """
    pointlist=[]

    params = cv2.SimpleBlobDetector_Params()
    params.filterByCircularity = False

    # Set up the detector with default parameters.
    detector = cv2.SimpleBlobDetector_create(params)

    # Detect blobs.
    keypoints = detector.detect(mask)

    ptList = []

    for key in keypoints:
        ptList.append((int(key.pt[0]),int(key.pt[1])))

    ptList = sort_coords(ptList)

    for pt in ptList:
        pointlist.append(pt)

    return pointlist

def createspots(pointlist):
    """
    creates spot objects and populates the spolist
    """
    spotlist = []
    for pt in pointlist:
        spotlist.append(Pkspot(pt[0], pt[1]))

    for i in range(len(spotlist)):
        spotlist[0].setspotnum(i)

    return spotlist

def setoccupancy(im_lot,spotlist,im_empty_lot):
    """
    sets the state of all pkspots in the
    spotlist
    """

    f = open('lotdata.txt','w')

    count = 0
    for spot in spotlist:
        if isoccupied(spot,im_lot,im_empty_lot,count) == True:
            spot.setoccupied(True)
            taken = 1
        else:
            spot.setoccupied(False)
            taken = 0

        count+=1

        f.write("F 20 "+str(count)+" "+str(spot.x)+" "+str(spot.y)+" "+str(taken)+"\n")

    f.close()

    UploadData.Upload()
    return spotlist

def isoccupied(spot,imlot,im_empty_lot,count):
    x0, y0, x1, y1 = spot.getcoordinates()
    # crop_spot_empty = im_empty_lot[y0:y1, x0:x1]


    im_empty_lot = cv2.imread('pk_large/pk_large_empty.jpg', cv2.IMREAD_GRAYSCALE)
    crop_spot_empty = im_empty_lot[0:y1-y0,0:x1-x0]
    # cv2.imshow("",crop_spot_empty)
    # cv2.waitKey(0)

    imlot = cv2.imread('pk_large/pk_large.jpg', cv2.IMREAD_GRAYSCALE)

    #cv2.imshow("",imlot)
    #cv2.waitKey(0)

    # imlot = cv2.bitwise_not(imlot,imlot)
    # testing contrast change
    # cv2.imshow("",imlot)
    # cv2.waitKey(0)



    r,imlot = cv2.threshold(imlot,90,255,3)
    crop_spot_occupied = imlot[y0:y1, x0:x1]



    """
    if count == 22:
        cv2.imshow("",crop_spot_occupied)
        cv2.waitKey(0)
    """



    diff = cv2.absdiff(crop_spot_occupied,crop_spot_empty)

    percentdiff = np.mean(diff)

    # print(percentdiff)
    return (percentdiff > 55)


def updateoccupancy(im_lot,spotlist,im_empty_lot):
    camera = cv2.VideoCapture(0)
    r,img = camera.read()
    time.sleep(1)
    del camera
    cv2.imwrite('pk_large/pk_large.jpg',img)
    setoccupancy(im_lot,spotlist,im_empty_lot)

    """
    count = 1
    for i in range(4):
        for j in range(len(spotlist)/4):
            print(str(count)+": "+str(spotlist[count-1].isoccupied))
            count+=1

        print("\n")
    print("\n")
    """


    return im_lot


def printlot(spotlist):
    boollist = []
    count = 0
    for spot in spotlist:
        if spot.isoccupied == True:
            boollist.append("*")
        else:
            boollist.append(" ")

    print("|"+boollist[39]+"|"+boollist[38]+"|"+boollist[37]+"|"+boollist[36]+"|"+boollist[35]+"|"+boollist[34]+"|"+
          boollist[33]+"|"+boollist[32]+"|"+boollist[31]+"|"+boollist[30]+"|")
    print("---------------------")
    print("|"+boollist[29]+"|"+boollist[28]+"|"+boollist[27]+"|"+boollist[26]+"|"+boollist[25]+"|"+boollist[24]+"|"+
          boollist[23]+"|"+boollist[22]+"|"+boollist[21]+"|"+boollist[20]+"|")
    print("---------------------")
    print("|"+boollist[19]+"|"+boollist[18]+"|"+boollist[17]+"|"+boollist[16]+"|"+boollist[15]+"|"+boollist[14]+"|"+
          boollist[13]+"|"+boollist[12]+"|"+boollist[11]+"|"+boollist[10]+"|")
    print("---------------------")
    print("|"+boollist[9]+"|"+boollist[8]+"|"+boollist[7]+"|"+boollist[6]+"|"+boollist[5]+"|"+boollist[4]+"|"+
          boollist[3]+"|"+boollist[2]+"|"+boollist[1]+"|"+boollist[0]+"|")
    print("---------------------")

    ct = 0
    for b in boollist:
        if b != "*":
            ct+=1
    print(str(ct)+" spot(s) free")
    print("")

def main():
    im_empty_lot = cv2.imread("pk_large/pk_large_empty.jpg",cv2.IMREAD_GRAYSCALE)

    im_mask = cv2.imread("pk_large/pk_large_mask.jpg",cv2.IMREAD_GRAYSCALE)

    camera = cv2.VideoCapture(0)
    r,img = camera.read()
    time.sleep(1)
    del camera
    cv2.imwrite('pk_large/pk_large.jpg',img)
    im_lot = cv2.imread('pl_large/pk_large.jpg',cv2.IMREAD_GRAYSCALE)


    pointlist = readmask(im_mask)
    pointlist = sort_coords(pointlist)
    spotlist = createspots(pointlist)

    """
    for p in spotlist:
        print(str(p.x)+" "+str(p.y))
    """

    spotlist = setoccupancy(im_lot,spotlist,im_empty_lot)

    while(True):
        im_lot = updateoccupancy(im_lot,spotlist,im_empty_lot)

        printlot(spotlist)
        time.sleep(2)

main()

