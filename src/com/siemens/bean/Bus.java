package com.siemens.bean;

public class Bus {
	private String busID;
	private String busName;
	private int busCapacity;
	private double busCostPKPM;
	private String type;
	
	public Bus() {
		// TODO Auto-generated constructor stub
	}
	public Bus(String busID, String busName, int busCapacity, double busCostPKPM, String type) {
		super();
		this.busID = busID;
		this.busName = busName;
		this.busCapacity = busCapacity;
		this.busCostPKPM = busCostPKPM;
		this.type = type;
	}
	
	/**
	 * @return the busID
	 */
	public String getBusID() {
		return busID;
	}

	/**
	 * @param busID the busID to set
	 */
	public void setBusID(String busID) {
		this.busID = busID;
	}

	/**
	 * @return the busName
	 */
	public String getBusName() {
		return busName;
	}

	/**
	 * @param busName the busName to set
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}

	/**
	 * @return the busCapacity
	 */
	public int getBusCapacity() {
		return busCapacity;
	}

	/**
	 * @param busCapacity the busCapacity to set
	 */
	public void setBusCapacity(int busCapacity) {
		this.busCapacity = busCapacity;
	}

	/**
	 * @return the busCostPKPM
	 */
	public double getBusCostPKPM() {
		return busCostPKPM;
	}

	/**
	 * @param busCostPKPM the busCostPKPM to set
	 */
	public void setBusCostPKPM(double busCostPKPM) {
		this.busCostPKPM = busCostPKPM;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bus [busID=" + busID + ", busName=" + busName + ", busCapacity=" + busCapacity + ", busCostPKPM="
				+ busCostPKPM + ", type=" + type + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + busCapacity;
		long temp;
		temp = Double.doubleToLongBits(busCostPKPM);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((busID == null) ? 0 : busID.hashCode());
		result = prime * result + ((busName == null) ? 0 : busName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Bus)) {
			return false;
		}
		Bus other = (Bus) obj;
		if (busCapacity != other.busCapacity) {
			return false;
		}
		if (Double.doubleToLongBits(busCostPKPM) != Double.doubleToLongBits(other.busCostPKPM)) {
			return false;
		}
		if (busID == null) {
			if (other.busID != null) {
				return false;
			}
		} else if (!busID.equals(other.busID)) {
			return false;
		}
		if (busName == null) {
			if (other.busName != null) {
				return false;
			}
		} else if (!busName.equals(other.busName)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	
	
}
