"""
file: Random - fgdf
language: python 3
author: rnb7880@rit.edu rachel n baumgarten
description:
"""

"""
Imports
"""
import random as r


"""
Classes
"""

"""
Functions
"""
def rand_coord(num):
    lst = []
    for i in range (num):
        a = r.randint(0,9)
        b = r.randint(0,9)
        tup = (a,b)
        lst.append(tup)
    return lst

def sort_coords(lst):
        insertionSort(lst,len(lst))
        return lst

def compare_ycoords(coord1,coord2):
    if coord1[1] > coord2[1]:
        return True
    if coord1[1] == coord2[1]:
        return compare_xcoords(coord1,coord2)
    else:
        return False

def compare_xcoords(coord1,coord2):
    if coord1[0] > coord2[0]:
        return True
    else:
        return False
def swap(list,x,y):
    """
    swap will take in a list and indices x and y and swap the values at x and y within the list
    :param list:
    :param x:
    :param y:
    :return:
    """
    tempVar = list[x]
    list[x] = list [y]
    list[y] = tempVar
def insertionSort(list,index):
    """
    insertionSort takes in a list and a starting index and sorts the list iteratively by taking the value at each index
    and sorting it in the proper place within the list. It will return the sorted list.
    :param list:
    :param index:
    :return:
    """
    for index in range(0,len(list)-1):
        while index > -1 and compare_ycoords(list[index],list[index +1]):
            if index == len(list):
                break
            swap(list,index,index+1)
            index -=1
    return list


def main():
    """
    """

    coords = rand_coord(10)
    print(coords)
    sort_coords(coords)
    print(coords)




if __name__ == "__main__":
    main()