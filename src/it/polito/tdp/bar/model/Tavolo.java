package it.polito.tdp.bar.model;

public class Tavolo {

	private final int code;
	private final int posti;
	private boolean isFree = true;
	
	/**
	 * @param code
	 * @param posti
	 */
	public Tavolo(int code, int posti) {
		this.code = code;
		this.posti = posti;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the posti
	 */
	public int getPosti() {
		return posti;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (code != other.code)
			return false;
		return true;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tavolo [code=" + code + ", posti=" + posti + ", isFree=" + isFree + "]";
	}

	
	
}
