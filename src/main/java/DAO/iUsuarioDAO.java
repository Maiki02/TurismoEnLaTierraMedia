package DAO;

import java.util.*;

import producto.*;
import usuario.*;

public interface iUsuarioDAO  {
    
	public List<Usuario> listarUsuarios(Map<Integer, Atraccion> mapDeAtraccionesPorID, 
			Map<Integer, Promocion> mapDePromocionesPorID);

	public int actualizar(Usuario usuario);
}