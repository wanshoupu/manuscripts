from zoo_veterinary_api import *


def veterinaryExam(zoo):
    for animal in zoo.getCollection():
        bodyWeight(animal)
        if isInsect(animal):
            checkThorax(animal)
            checkExoskeleton(animal)
            continue
        # vertebrates below
        xRay(animal)
        if isFish(animal):
            checkFin(animal)
            checkScale(animal)
            continue
        # warm-blooded below
        checkTempterature(animal)
        if isBird(animal):
            checkFeather(animal)
        elif isMammal(animal):
            dentalClinic(animal)
