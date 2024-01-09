package com.electricity.project.realtimecalculations.api.production;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link PowerProductionDTO}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutablePowerProductionDTO.builder()}.
 */
@Generated(from = "PowerProductionDTO", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutablePowerProductionDTO
    implements PowerProductionDTO {
  private final Long id;
  private final String ipv6Address;
  private final PowerStationState state;
  private final Long producedPower;
  private final LocalDateTime timestamp;

  private ImmutablePowerProductionDTO(
      Long id,
      String ipv6Address,
      PowerStationState state,
      Long producedPower,
      LocalDateTime timestamp) {
    this.id = id;
    this.ipv6Address = ipv6Address;
    this.state = state;
    this.producedPower = producedPower;
    this.timestamp = timestamp;
  }

  /**
   * @return The value of the {@code id} attribute
   */
  @JsonProperty("id")
  @Override
  public Optional<Long> getId() {
    return Optional.ofNullable(id);
  }

  /**
   * @return The value of the {@code ipv6Address} attribute
   */
  @JsonProperty(value = "ipv6Address", required = true)
  @Override
  public String getIpv6Address() {
    return ipv6Address;
  }

  /**
   * @return The value of the {@code state} attribute
   */
  @JsonProperty(value = "state", required = true)
  @Override
  public PowerStationState getState() {
    return state;
  }

  /**
   * @return The value of the {@code producedPower} attribute
   */
  @JsonProperty(value = "power", required = true)
  @Override
  public Long getProducedPower() {
    return producedPower;
  }

  /**
   * @return The value of the {@code timestamp} attribute
   */
  @JsonProperty(value = "timestamp", required = true)
  @Override
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Copy the current immutable object by setting a <i>present</i> value for the optional {@link PowerProductionDTO#getId() id} attribute.
   * @param value The value for id
   * @return A modified copy of {@code this} object
   */
  public final ImmutablePowerProductionDTO withId(long value) {
    Long newValue = value;
    if (Objects.equals(this.id, newValue)) return this;
    return new ImmutablePowerProductionDTO(newValue, this.ipv6Address, this.state, this.producedPower, this.timestamp);
  }

  /**
   * Copy the current immutable object by setting an optional value for the {@link PowerProductionDTO#getId() id} attribute.
   * An equality check is used on inner nullable value to prevent copying of the same value by returning {@code this}.
   * @param optional A value for id
   * @return A modified copy of {@code this} object
   */
  public final ImmutablePowerProductionDTO withId(Optional<Long> optional) {
    Long value = optional.orElse(null);
    if (Objects.equals(this.id, value)) return this;
    return new ImmutablePowerProductionDTO(value, this.ipv6Address, this.state, this.producedPower, this.timestamp);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PowerProductionDTO#getIpv6Address() ipv6Address} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for ipv6Address
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePowerProductionDTO withIpv6Address(String value) {
    String newValue = Objects.requireNonNull(value, "ipv6Address");
    if (this.ipv6Address.equals(newValue)) return this;
    return new ImmutablePowerProductionDTO(this.id, newValue, this.state, this.producedPower, this.timestamp);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PowerProductionDTO#getState() state} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for state
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePowerProductionDTO withState(PowerStationState value) {
    PowerStationState newValue = Objects.requireNonNull(value, "state");
    if (this.state == newValue) return this;
    return new ImmutablePowerProductionDTO(this.id, this.ipv6Address, newValue, this.producedPower, this.timestamp);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PowerProductionDTO#getProducedPower() producedPower} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for producedPower
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePowerProductionDTO withProducedPower(Long value) {
    Long newValue = Objects.requireNonNull(value, "producedPower");
    if (this.producedPower.equals(newValue)) return this;
    return new ImmutablePowerProductionDTO(this.id, this.ipv6Address, this.state, newValue, this.timestamp);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PowerProductionDTO#getTimestamp() timestamp} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for timestamp
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePowerProductionDTO withTimestamp(LocalDateTime value) {
    if (this.timestamp == value) return this;
    LocalDateTime newValue = Objects.requireNonNull(value, "timestamp");
    return new ImmutablePowerProductionDTO(this.id, this.ipv6Address, this.state, this.producedPower, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePowerProductionDTO} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePowerProductionDTO
        && equalTo(0, (ImmutablePowerProductionDTO) another);
  }

  private boolean equalTo(int synthetic, ImmutablePowerProductionDTO another) {
    return Objects.equals(id, another.id)
        && ipv6Address.equals(another.ipv6Address)
        && state.equals(another.state)
        && producedPower.equals(another.producedPower)
        && timestamp.equals(another.timestamp);
  }

  /**
   * Computes a hash code from attributes: {@code id}, {@code ipv6Address}, {@code state}, {@code producedPower}, {@code timestamp}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Objects.hashCode(id);
    h += (h << 5) + ipv6Address.hashCode();
    h += (h << 5) + state.hashCode();
    h += (h << 5) + producedPower.hashCode();
    h += (h << 5) + timestamp.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code PowerProductionDTO} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("PowerProductionDTO{");
    if (id != null) {
      builder.append("id=").append(id);
    }
    if (builder.length() > 19) builder.append(", ");
    builder.append("ipv6Address=").append(ipv6Address);
    builder.append(", ");
    builder.append("state=").append(state);
    builder.append(", ");
    builder.append("producedPower=").append(producedPower);
    builder.append(", ");
    builder.append("timestamp=").append(timestamp);
    return builder.append("}").toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "PowerProductionDTO", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements PowerProductionDTO {
    Optional<Long> id = Optional.empty();
    String ipv6Address;
    PowerStationState state;
    Long producedPower;
    LocalDateTime timestamp;
    @JsonProperty("id")
    public void setId(Optional<Long> id) {
      this.id = id;
    }
    @JsonProperty(value = "ipv6Address", required = true)
    public void setIpv6Address(String ipv6Address) {
      this.ipv6Address = ipv6Address;
    }
    @JsonProperty(value = "state", required = true)
    public void setState(PowerStationState state) {
      this.state = state;
    }
    @JsonProperty(value = "power", required = true)
    public void setProducedPower(Long producedPower) {
      this.producedPower = producedPower;
    }
    @JsonProperty(value = "timestamp", required = true)
    public void setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
    }
    @Override
    public Optional<Long> getId() { throw new UnsupportedOperationException(); }
    @Override
    public String getIpv6Address() { throw new UnsupportedOperationException(); }
    @Override
    public PowerStationState getState() { throw new UnsupportedOperationException(); }
    @Override
    public Long getProducedPower() { throw new UnsupportedOperationException(); }
    @Override
    public LocalDateTime getTimestamp() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutablePowerProductionDTO fromJson(Json json) {
    ImmutablePowerProductionDTO.Builder builder = ImmutablePowerProductionDTO.builder();
    if (json.id != null) {
      builder.id(json.id);
    }
    if (json.ipv6Address != null) {
      builder.ipv6Address(json.ipv6Address);
    }
    if (json.state != null) {
      builder.state(json.state);
    }
    if (json.producedPower != null) {
      builder.producedPower(json.producedPower);
    }
    if (json.timestamp != null) {
      builder.timestamp(json.timestamp);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link PowerProductionDTO} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable PowerProductionDTO instance
   */
  public static ImmutablePowerProductionDTO copyOf(PowerProductionDTO instance) {
    if (instance instanceof ImmutablePowerProductionDTO) {
      return (ImmutablePowerProductionDTO) instance;
    }
    return ImmutablePowerProductionDTO.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutablePowerProductionDTO ImmutablePowerProductionDTO}.
   * <pre>
   * ImmutablePowerProductionDTO.builder()
   *    .id(Long) // optional {@link PowerProductionDTO#getId() id}
   *    .ipv6Address(String) // required {@link PowerProductionDTO#getIpv6Address() ipv6Address}
   *    .state(com.electricity.project.realtimecalculations.api.production.PowerStationState) // required {@link PowerProductionDTO#getState() state}
   *    .producedPower(Long) // required {@link PowerProductionDTO#getProducedPower() producedPower}
   *    .timestamp(java.time.LocalDateTime) // required {@link PowerProductionDTO#getTimestamp() timestamp}
   *    .build();
   * </pre>
   * @return A new ImmutablePowerProductionDTO builder
   */
  public static ImmutablePowerProductionDTO.Builder builder() {
    return new ImmutablePowerProductionDTO.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePowerProductionDTO ImmutablePowerProductionDTO}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "PowerProductionDTO", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_IPV6_ADDRESS = 0x1L;
    private static final long INIT_BIT_STATE = 0x2L;
    private static final long INIT_BIT_PRODUCED_POWER = 0x4L;
    private static final long INIT_BIT_TIMESTAMP = 0x8L;
    private long initBits = 0xfL;

    private Long id;
    private String ipv6Address;
    private PowerStationState state;
    private Long producedPower;
    private LocalDateTime timestamp;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code PowerProductionDTO} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PowerProductionDTO instance) {
      Objects.requireNonNull(instance, "instance");
      Optional<Long> idOptional = instance.getId();
      if (idOptional.isPresent()) {
        id(idOptional);
      }
      ipv6Address(instance.getIpv6Address());
      state(instance.getState());
      producedPower(instance.getProducedPower());
      timestamp(instance.getTimestamp());
      return this;
    }

    /**
     * Initializes the optional value {@link PowerProductionDTO#getId() id} to id.
     * @param id The value for id
     * @return {@code this} builder for chained invocation
     */
    public final Builder id(long id) {
      this.id = id;
      return this;
    }

    /**
     * Initializes the optional value {@link PowerProductionDTO#getId() id} to id.
     * @param id The value for id
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("id")
    public final Builder id(Optional<Long> id) {
      this.id = id.orElse(null);
      return this;
    }

    /**
     * Initializes the value for the {@link PowerProductionDTO#getIpv6Address() ipv6Address} attribute.
     * @param ipv6Address The value for ipv6Address 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty(value = "ipv6Address", required = true)
    public final Builder ipv6Address(String ipv6Address) {
      this.ipv6Address = Objects.requireNonNull(ipv6Address, "ipv6Address");
      initBits &= ~INIT_BIT_IPV6_ADDRESS;
      return this;
    }

    /**
     * Initializes the value for the {@link PowerProductionDTO#getState() state} attribute.
     * @param state The value for state 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty(value = "state", required = true)
    public final Builder state(PowerStationState state) {
      this.state = Objects.requireNonNull(state, "state");
      initBits &= ~INIT_BIT_STATE;
      return this;
    }

    /**
     * Initializes the value for the {@link PowerProductionDTO#getProducedPower() producedPower} attribute.
     * @param producedPower The value for producedPower 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty(value = "power", required = true)
    public final Builder producedPower(Long producedPower) {
      this.producedPower = Objects.requireNonNull(producedPower, "producedPower");
      initBits &= ~INIT_BIT_PRODUCED_POWER;
      return this;
    }

    /**
     * Initializes the value for the {@link PowerProductionDTO#getTimestamp() timestamp} attribute.
     * @param timestamp The value for timestamp 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty(value = "timestamp", required = true)
    public final Builder timestamp(LocalDateTime timestamp) {
      this.timestamp = Objects.requireNonNull(timestamp, "timestamp");
      initBits &= ~INIT_BIT_TIMESTAMP;
      return this;
    }

    /**
     * Builds a new {@link ImmutablePowerProductionDTO ImmutablePowerProductionDTO}.
     * @return An immutable instance of PowerProductionDTO
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePowerProductionDTO build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePowerProductionDTO(id, ipv6Address, state, producedPower, timestamp);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_IPV6_ADDRESS) != 0) attributes.add("ipv6Address");
      if ((initBits & INIT_BIT_STATE) != 0) attributes.add("state");
      if ((initBits & INIT_BIT_PRODUCED_POWER) != 0) attributes.add("producedPower");
      if ((initBits & INIT_BIT_TIMESTAMP) != 0) attributes.add("timestamp");
      return "Cannot build PowerProductionDTO, some of required attributes are not set " + attributes;
    }
  }
}
