package util;

public abstract class Entity {
	
	public Long _version;
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		
		if (other == null || !(other instanceof Entity) || getId() == null) {
			return false;
		}
		
		Entity entity = (Entity) other;
		return getId().equals(entity.getId());
	}
	
	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		
		return super.hashCode();
	}
	
	public abstract String getId();
	
	public abstract void setId(String id);
	
	public abstract String getHref();
	
	public abstract void setHref(String href);
}
