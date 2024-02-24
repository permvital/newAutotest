package data;

public enum EnglishLevelData {
  BEGGINER("Начальный уровень (Beginner)"),
  ELEMENTARY("Элементарный уровень (Elementary)"),
  PREINTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
  INTERMEDIATE("Средний (Intermediate)"),
  UPPREINTERMEDIATE("Выше среднего (Upper Intermediate)"),
  ADVANCED("Продвинутый (Advanced)"),
  MASTERY("Супер продвинутый (Mastery)");

  private String name;

  EnglishLevelData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}