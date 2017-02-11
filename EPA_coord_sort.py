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
        lst.append(r.randint * 10,r.randint*10)
    return lst

def sort_coords(lst):
        insertionSort(lst,0)
        return lst


def compare_ycoords(coord1,coord2):
    if coord1(1) > coord1(1):
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
        while index > -1 and compare_ycoords(list[index],list [index +1]):
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