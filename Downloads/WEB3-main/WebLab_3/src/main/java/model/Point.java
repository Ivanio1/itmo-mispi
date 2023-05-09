package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "points")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Point implements Cloneable, Serializable {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@SequenceGenerator(name = "PointsIdSeq", sequenceName = "points_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PointsIdSeq")
	private Long id;
	
	@Column(name = "x", nullable = false)
	private Double x;
	
	@Column(name = "y", nullable = false)
	private Double y;
	
	@Column(name = "r", nullable = false)
	private Double r;
	
	@Column(name = "checked", nullable = false)
	private Boolean result;
    @Column(name = "currtime", nullable = false)
    private String curr_time;
    @Column(name = "extime", nullable = false)
    private String ex_time;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
