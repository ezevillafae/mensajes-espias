package com.example.mensajesespias.domain.spy;

import java.util.Objects;


public final class Spy {

  private final Integer id;
  private final String name;

  public Spy(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer id() {
    return id;
  }

  public String name() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Spy spy = (Spy) o;
    return Objects.equals(id, spy.id) && Objects.equals(name, spy.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Spy{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
