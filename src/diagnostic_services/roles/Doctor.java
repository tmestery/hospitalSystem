package roles;

import diagnostic_services.file_system.Biopsy.Category;

public class Doctor implements HealthCareProvider {
  private final String firstName;
  private final String lastName;
  private final int id;
  private final Specialty specialty;

  public Doctor(String firstName, String lastName, int id, Specialty specialty) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.specialty = specialty;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public int getId() {
    return id;
  }

  public Category getCategory() {
    return specialty.toCategory();
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }

  public static enum Specialty {
    RADIOLOGY, SURGEON, UROLOGY, DERMATOLOGY;

    public Category toCategory() {
      switch (this) {
        case RADIOLOGY -> {
            return Category.NEEDLE;
            }
        case SURGEON -> {
            return Category.SURGICAL;
            }
        case UROLOGY -> {
            return Category.ENDOSCOPIC;
            }
        case DERMATOLOGY -> {
            return Category.DERMATOLOGIC;
            }
        default -> throw new IllegalArgumentException("Unknown specialty: " + this);
      }
    }

    public static Specialty fromCategory(Category c) {
      switch (c) {
        case NEEDLE -> {
            return RADIOLOGY;
          }
        case SURGICAL -> {
            return SURGEON;
          }
        case ENDOSCOPIC -> {
            return UROLOGY;
          }
        case DERMATOLOGIC -> {
            return DERMATOLOGY;
          }
        default -> throw new IllegalArgumentException("Unknown category: " + c);
      }
    }
  }
}
