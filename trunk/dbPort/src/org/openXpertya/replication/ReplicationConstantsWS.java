package org.openXpertya.replication;

import java.util.ArrayList;
import java.util.HashMap;

public class ReplicationConstantsWS extends ReplicationConstants {

	/*
	 * Valores generales
	 */
	/** Cantidad de eventos a enviar en una misma llamada al WS */
	public static int EVENTS_PER_CALL = 100;
	/** TimeOut por defecto por llamado a WS (ms). */
	public static int TIME_OUT_BASE = 60000;
	/** TimeOut adicional en funcion del numero de eventos a enviar. */
	public static int TIME_OUT_EXTRA_FACTOR = 100;
	
	/*
	 * En WS no se limita el numero de reintentos.  Solo se usa RETRY1 para diferenciar OK de ERROR.
	 */
	/* ============= COLECCIONES ============= */
	/** Conjunto: Estados en los que se deberá replicar */
	public static ArrayList<Character> replicateStates = new ArrayList<Character>();
	/** Conjunto: Estados de error de replicacion */
	public static ArrayList<Character> errorStates = new ArrayList<Character>();
	/** Dado un estado, obtener el siguiente estado si se recibio un OK */
	public static HashMap<Character, Character> nextStatusWhenOK = new HashMap<Character, Character>();
	/** Dado un estado, obtener el siguiente estado si se recibio un ERROR */
	public static HashMap<Character, Character> nextStatusWhenERR = new HashMap<Character, Character>();
	
	
	static 
	{
		// Estados de replicacion en WS
		replicateStates.clear();
		replicateStates.add(REPARRAY_REPLICATE_INSERT);
		replicateStates.add(REPARRAY_REPLICATE_MODIFICATION);
		replicateStates.add(REPARRAY_RETRY1);
		
		// Estados de error
		errorStates.clear();
		errorStates.add(REPARRAY_RETRY1);

		// Si recibimos un OK y dado un estado, el siguiente estado será...
		nextStatusWhenOK.clear();
		nextStatusWhenOK.put(ReplicationConstants.REPARRAY_REPLICATE_INSERT, ReplicationConstants.REPARRAY_REPLICATED);
		nextStatusWhenOK.put(ReplicationConstants.REPARRAY_REPLICATE_MODIFICATION, ReplicationConstants.REPARRAY_REPLICATED);
		nextStatusWhenOK.put(ReplicationConstants.REPARRAY_RETRY1, ReplicationConstants.REPARRAY_REPLICATED);

		// Si recibimos un ERROR y dado un estado, el siguiente estado será...
		nextStatusWhenERR.clear();
		nextStatusWhenERR.put(ReplicationConstants.REPARRAY_REPLICATE_INSERT, ReplicationConstants.REPARRAY_RETRY1);
		nextStatusWhenERR.put(ReplicationConstants.REPARRAY_REPLICATE_MODIFICATION, ReplicationConstants.REPARRAY_RETRY1);
		nextStatusWhenERR.put(ReplicationConstants.REPARRAY_RETRY1, ReplicationConstants.REPARRAY_RETRY1);

	}
}
