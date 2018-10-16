package mx.ine.sif.dao;

import java.util.List;

public interface DAOAdminPermisosInterface {

	List<Integer> getPermisosEnModulo(int idSistema, int idModulo,
			int rolUsuario, int anio);

}
