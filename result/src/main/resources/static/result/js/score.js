document.addEventListener('DOMContentLoaded', () => {
    const studentSelect = document.getElementById('studentSelect');
    const studentDetailsSection = document.getElementById('studentDetailsSection');
    const courseContainer = document.getElementById('courseContainer');
    const scoreForm = document.getElementById('scoreForm');
    const resetBtn = document.getElementById('resetBtn');

    let studentsMap = new Map();

    // Fetch all students and populate dropdown
    fetch('http://localhost:8001/student/all')
        .then(response => response.json())
        .then(students => {
            studentSelect.innerHTML = '<option value="">-- Select Student --</option>';
            students.forEach(student => {
                studentsMap.set(student.id.toString(), student);
                const option = document.createElement('option');
                option.value = student.id;
                option.textContent = student.name;
                studentSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching students:', error);
            studentSelect.innerHTML = '<option value="">Error loading students</option>';
        });

    studentSelect.addEventListener('change', (e) => {
        const studentId = e.target.value;
        if (!studentId) {
            studentDetailsSection.style.display = 'none';
            return;
        }

        const student = studentsMap.get(studentId);
        if (student) {
            document.getElementById('studentNameDisplay').value = student.name;
            document.getElementById('studentAgeDisplay').value = student.age;

            // Fetch department details (including courses) for this student
            if (student.departmentId) {
                fetch(`http://localhost:8001/department/get/${student.departmentId}`)
                    .then(response => response.json())
                    .then(department => {
                        document.getElementById('studentDeptDisplay').value = department.departmentName;
                        populateCourses(department.courses);
                        studentDetailsSection.style.display = 'block';
                    })
                    .catch(error => {
                        console.error('Error fetching department:', error);
                        document.getElementById('studentDeptDisplay').value = "Error loading";
                        courseContainer.innerHTML = '<p class="error">Failed to load courses</p>';
                        studentDetailsSection.style.display = 'block';
                    });
            } else {
                document.getElementById('studentDeptDisplay').value = "N/A";
                courseContainer.innerHTML = '<p>No department assigned</p>';
                studentDetailsSection.style.display = 'block';
            }
        }
    });

    function populateCourses(courses) {
        courseContainer.innerHTML = '';
        if (!courses || courses.length === 0) {
            courseContainer.innerHTML = '<p>No courses found for this department.</p>';
            return;
        }

        courses.forEach(course => {
            const courseRow = document.createElement('div');
            courseRow.className = 'row course-row';
            courseRow.style.marginBottom = '15px';
            courseRow.style.alignItems = 'center';
            
            courseRow.innerHTML = `
                <div class="form-group" style="flex: 2;">
                    <input type="text" readonly disabled value="${course.courseName}">
                </div>
                <div class="form-group form-group--narrow" style="flex: 1;">
                    <input type="number" name="marks_${course.id}" min="0" max="100" placeholder="Marks" required data-course-id="${course.id}" class="mark-input">
                </div>
            `;
            courseContainer.appendChild(courseRow);
        });
    }

    scoreForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const studentId = studentSelect.value;
        if (!studentId) {
            alert('Please select a student.');
            return;
        }

        const markInputs = document.querySelectorAll('.mark-input');
        const scores = Array.from(markInputs).map(input => ({
            courseId: input.dataset.courseId,
            marks: parseInt(input.value, 10)
        }));

        const payload = {
            studentId: parseInt(studentId, 10),
            scores: scores
        };

        const submitBtn = scoreForm.querySelector('.btn--save');
        const originalText = submitBtn.innerHTML;
        submitBtn.innerHTML = 'Saving...';
        submitBtn.disabled = true;

        fetch('/score/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (response.ok) {
                alert('Scores saved successfully!');
                scoreForm.reset();
                studentDetailsSection.style.display = 'none';
            } else {
                throw new Error('Failed to save scores');
            }
        })
        .catch(error => {
            console.error('Error saving scores:', error);
            alert('Error saving scores.');
        })
        .finally(() => {
            submitBtn.innerHTML = originalText;
            submitBtn.disabled = false;
        });
    });

    resetBtn.addEventListener('click', () => {
        studentDetailsSection.style.display = 'none';
    });
});
