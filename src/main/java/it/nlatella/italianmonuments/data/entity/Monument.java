package it.nlatella.italianmonuments.data.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.processor.PreAssignmentProcessor;

import it.nlatella.italianmonuments.opencsv.ConvertEmptyOrBlankStringsToDefault;

@Document(indexName = "monumentindex")
public class Monument {

	@Id
	private String id;

	@CsvBindByPosition(position = 0)
	@Field(type = FieldType.Text, name = "municipality")
	private String municipality;

	@CsvBindByPosition(position = 1)
	@Field(type = FieldType.Text, name = "province")
	private String province;

	@CsvBindByPosition(position = 2)
	@Field(type = FieldType.Text, name = "region")
	private String region;

	@PreAssignmentProcessor(processor = ConvertEmptyOrBlankStringsToDefault.class, paramString = "ND")
	@CsvBindByPosition(position = 3)
	@Field(type = FieldType.Text, name = "name")
	private String name;

	@CsvBindByPosition(position = 4)
	@Field(type = FieldType.Text, name = "type")
	private String type;

	@CsvBindByPosition(position = 5)
	@Field(type = FieldType.Integer, name = "censusYear")
	private Integer censusYear;

	@CsvDate(value = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@CsvBindByPosition(position = 6)
	@Field(type = FieldType.Date, name = "censusTimestamp", format = DateFormat.date_time_no_millis)
	private Date censusTimestamp;

	@CsvBindByPosition(position = 7)
	@Field(type = FieldType.Text, name = "openStreetMapIdentifier")
	private String openStreetMapIdentifier;

	@CsvBindByPosition(position = 8)
	@Field(type = FieldType.Double, name = "longitude")
	private Double longitude;

	@CsvBindByPosition(position = 9)
	@Field(type = FieldType.Double, name = "latitude")
	private Double latitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(Integer censusYear) {
		this.censusYear = censusYear;
	}

	public Date getCensusTimestamp() {
		return censusTimestamp;
	}

	public void setCensusTimestamp(Date censusTimestamp) {
		this.censusTimestamp = censusTimestamp;
	}

	public String getOpenStreetMapIdentifier() {
		return openStreetMapIdentifier;
	}

	public void setOpenStreetMapIdentifier(String openStreetMapIdentifier) {
		this.openStreetMapIdentifier = openStreetMapIdentifier;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((censusTimestamp == null) ? 0 : censusTimestamp.hashCode());
		result = prime * result + ((censusYear == null) ? 0 : censusYear.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((municipality == null) ? 0 : municipality.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((openStreetMapIdentifier == null) ? 0 : openStreetMapIdentifier.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monument other = (Monument) obj;
		if (censusTimestamp == null) {
			if (other.censusTimestamp != null)
				return false;
		} else if (!censusTimestamp.equals(other.censusTimestamp))
			return false;
		if (censusYear == null) {
			if (other.censusYear != null)
				return false;
		} else if (!censusYear.equals(other.censusYear))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (municipality == null) {
			if (other.municipality != null)
				return false;
		} else if (!municipality.equals(other.municipality))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openStreetMapIdentifier == null) {
			if (other.openStreetMapIdentifier != null)
				return false;
		} else if (!openStreetMapIdentifier.equals(other.openStreetMapIdentifier))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Monument [id=" + id + ", municipality=" + municipality + ", province=" + province + ", region=" + region
				+ ", name=" + name + ", type=" + type + ", censusYear=" + censusYear + ", censusTimestamp="
				+ censusTimestamp + ", openStreetMapIdentifier=" + openStreetMapIdentifier + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}

}
