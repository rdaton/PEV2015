package individuo;

import java.lang.reflect.Constructor;

public abstract class Gen {

	Object bit;

	abstract Gen clone_aux();

	public abstract Object toInt();

	public Gen clone() {
		Gen unGen = clone_aux();
		return unGen;
	}

	abstract public boolean equals(Gen g2);

	@SuppressWarnings("rawtypes")
	public Gen newInstance() {
        try {
        	Constructor unConstructor = this.getClass().getDeclaredConstructors()[0];
        	return (Gen) unConstructor.newInstance();
        } catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    	}
        return null;
    }

	abstract public boolean estaVacio();
	
	abstract public String toString();

}
