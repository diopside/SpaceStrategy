package entities;

public interface Removeable {

	public void setDeconstructionStatus(boolean b);
	public boolean isBeingDeconstructed();
	public void remove(Planet p);
	
}
