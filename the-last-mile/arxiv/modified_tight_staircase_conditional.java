...
List<Animal> list = ...
Animal animal = null;
if (sound == null)
    animal =
      new MuteAnimal();
else if (
  soundsTrumpet(sound))
    animal =
      new Elephant();
...
else if (soundsHiss(
  amplify(sound)))
    animal = new Snake();
else if (soundsBuzz(
  amplify(sound)))
    animal = new Bee();
else
    animal = new Mystery();
list.add(animal);
...
