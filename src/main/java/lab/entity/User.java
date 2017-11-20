package lab.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 2063867043555106905L;

	private String name;
	private String token;

	public boolean isValido() {
		return name != null && !name.isEmpty();
	}
}
