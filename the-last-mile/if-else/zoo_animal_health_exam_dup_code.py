from zoo_veterinary_api import *


def veterinaryExam(zoo):
    for animal in zoo.getCollection():
        bodyWeight(animal)
        if isInsect(animal):
            checkThorax(animal)
            checkExoskeleton(animal)
        else:
            xRay(animal)
        # vertebrates below
        if isFish(animal):
            checkFin(animal)
            checkScale(animal)
        # warm-blooded below
        if not isInsect(animal) and not isFish(animal):
            checkTempterature(animal)
        if isBird(animal):
            checkFeather(animal)
        elif isMammal(animal):
            dentalClinic(animal)
