package mx.ine.sif.util;

public enum EnumModulos {
	
	USUARIOS(1);
	private final int idModulo;
	
	EnumModulos(int idModulo) {
		this.idModulo = idModulo;
	}
	
	public int getIdModulo(){
		return this.idModulo;
	}
}
