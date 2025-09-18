package com.abc.crud.dtos;

import com.abc.crud.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    // Student → StudentDTO
    public static StudentDTO toStudentDTO(Student student) {
        if (student == null) return null;

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        if (student.getDepartment() != null) {
            DepartmentSummaryDTO depDto = new DepartmentSummaryDTO();
            depDto.setId(student.getDepartment().getId());
            depDto.setName(student.getDepartment().getName());
//            dto.setDepartment(depDto);
        }

        return dto;
    }

    // Department → DepartmentDTO
    public static DepartmentDTO toDepartmentDTO(Department department) {
        if (department == null) return null;

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());

        if (department.getStudents() != null) {
            List<StudentSummaryDTO> students = department.getStudents().stream()
                    .map(s -> {
                        StudentSummaryDTO summary = new StudentSummaryDTO();
                        summary.setId(s.getId());
                        summary.setFirstName(s.getFirstName());
                        summary.setLastName(s.getLastName());
                        return summary;
                    })
                    .toList();
//            dto.setStudents(students);
        }

        return dto;
    }
    public UserDto toDto(User user){
        if(user == null) return null;
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.username = user.getUsername();
        if(user.getProfile() != null){
            ProfileDto pd = new ProfileDto();
            pd.id = user.getProfile().getId();
            pd.firstName = user.getProfile().getFirstName();
            pd.lastName = user.getProfile().getLastName();
            dto.profile = pd;
        }
        return dto;
    }


    public User fromDto(UserDto dto){
        if(dto == null) return null;
        User user = new User();
        user.setUsername(dto.username);
        if(dto.profile != null){
            Profile profile = new Profile();
            profile.setFirstName(dto.profile.firstName);
            profile.setLastName(dto.profile.lastName);
            user.setProfile(profile);
        }
        return user;
    }
    public ProfileDto toDto(Profile profile){
        if(profile == null) return null;
        ProfileDto dto = new ProfileDto();
        dto.id = profile.getId();
        dto.firstName = profile.getFirstName();
        dto.lastName = profile.getLastName();
        return dto;
    }


    public Profile fromDto(ProfileDto dto){
        if(dto == null) return null;
        Profile profile = new Profile();
        profile.setFirstName(dto.firstName);
        profile.setLastName(dto.lastName);
        return profile;
    }


    //------------------
    public static StudentDTO toDTO(Student student) {
        if (student == null) return null;
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        if (student.getDepartment() != null) {
            DepartmentDTO dp = new DepartmentDTO();
            dp.setId(student.getDepartment().getId());
            dp.setName(student.getDepartment().getName());
            dto.setDepartmentDTO(dp);
        }
        if (student.getCourses() != null) {
            dto.setCourseIds(
                    student.getCourses().stream()
                            .map(Course::getId)
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        if (dto == null) return null;

        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());
        if (dto.getDepartmentDTO()!=null){
            Department dt = new Department();
            dt.setId(dto.getDepartmentDTO().getId());
            student.setDepartment(dt);
        }

//        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
//            Set<Course> courses = new HashSet<>(courseRepository.findAllById(dto.getCourseIds()));
//            student.getCourses().clear();   // <-- Important: clear old links
//            student.getCourses().addAll(courses); // add fresh set
//        }

        return student;
    }
    public static CourseDTO toDTO(Course course) {
        if (course == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());

        if (course.getStudents() != null) {
            dto.setStudentIds(
                    course.getStudents().stream()
                            .map(Student::getId)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        if (dto == null) return null;

        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        return course;
    }


}
