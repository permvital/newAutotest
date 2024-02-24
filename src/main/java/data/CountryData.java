package data;

public enum CountryData {
  RUSSIA("Россия");
  private String name;
  private CountryData countryData;

  CountryData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}