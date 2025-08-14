package com.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDTO {
	
    private Long id;

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Duration is required")
    private String duration;
    
    
  //Generating Setter And Getter Methods
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
    
       
}

