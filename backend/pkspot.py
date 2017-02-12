"""
the pkspot class represents a parking spot in a lot

@author Michael Baumgarten
@version 2/11//17
"""

class Pkspot:

    def __init__(self,xcord,ycord):
        """
        Constructor of the pkspot class

        :param xcord: the center x-coordiante of the spot
        :param ycord: the center y-coordinate of the spot
        """
        self.x = xcord
        self.y = ycord


    def getcoordinates(self):
        """
        :return: the top left coordinate and bottom right coordinate
        """
        coords = []
        bbx= 20
        bby = 30

        topx = self.x-35
        topy = self.y-30

        bottomx = self.x+25
        bottomy = self.y+40


        return topx,topy,bottomx,bottomy

    def setoccupied(self,isoccupied):
        """
        sets the state of occupancy
        :param isoccupied: true if spot is taken
        """
        self.isoccupied = isoccupied

    def setspotnum(self,num):
        """
        sets the number of this spot
        :param num: number of this spot
        """
        self.spotnum = num
