document.addEventListener('DOMContentLoaded', function () {
  const searchInput = document.getElementById('searchInput');

  searchInput.addEventListener('input', function (e) {
    const term = e.target.value.trim().toLowerCase();
    document.querySelectorAll('#deptTableBody tr').forEach(row => {
      if (row.classList.contains('empty-row')) return;
      row.style.display = row.textContent.toLowerCase().includes(term) ? '' : 'none';
    });
  });
});

function editDepartment(btn) {
  document.getElementById('deptName').value = btn.dataset.name;
  document.getElementById('degree').value = btn.dataset.degree;
  document.getElementById('duration').value = btn.dataset.duration;
  document.getElementById('deptName').focus();
}

function deleteDepartment(btn) {
  if (!confirm('Delete this department?')) return;
  alert('Delete endpoint not implemented yet on the backend.');
}

$(document).on('submit', '#deptForm', function (e) {
	debugger
	window.alert();
  e.preventDefault();
  const formData = new FormData(this);
  if (confirm('Do you want to save the department?')) {
    $.ajax({
      url: '/department/save',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      cache: false,
      beforeSend: function () {
        $('.btn--save').prop('disabled', true);
      },
      success: function () {
        alert('Department saved successfully.');
        $('#deptForm')[0].reset();
        location.reload();
      },
      error: function (xhr) {
        alert('Failed to save department.');
        console.error(xhr);
      },
      complete: function () {
        $('.btn--save').prop('disabled', false);
      }
    });
  }
});



  const addCourseBtn = document.getElementById('addCourseBtn');
  const courseContainer = document.getElementById('courseContainer');
  if (addCourseBtn && courseContainer) {
	debugger
	    let availableCourses = [];
    $.ajax({
      url: '/course/get',
      type: 'GET',
      success: function (data) {
        availableCourses = data;
        populateCourseSelects();
      },
      error: function (xhr) {
        console.error('Failed to fetch courses from /course/get, using fallback data:', xhr);
        availableCourses = [
          { courseName: 'Data Structures' },
          { courseName: 'Algorithms' },
          { courseName: 'Database Systems' },
          { courseName: 'Computer Networks' }
        ];
        populateCourseSelects();
      }
    });
    function populateCourseSelects() {
      document.querySelectorAll('.course-select').forEach(select => {
        const selectedValue = select.value;
        select.innerHTML = '<option value="" disabled selected>Select a Course...</option>';
        availableCourses.forEach(course => {
          const option = document.createElement('option');
          option.value = course.courseName;
          option.textContent = course.courseName;
          select.appendChild(option);
        });
        if (selectedValue) {
          select.value = selectedValue;
        }
      });
    }
    addCourseBtn.addEventListener('click', function () {
      const row = document.createElement('div');
      row.className = 'course-row';
      row.innerHTML = `
        <select name="courses[]" class="course-select">
          <option value="" disabled selected>Select a Course...</option>
        </select>
        <button type="button" class="icon-btn icon-btn--delete remove-course-btn" title="Remove Course">
          <svg viewBox="0 0 20 20" fill="none"><path d="M4.5 6h11M8 6V4.5h4V6M6 6l.6 9.4a1 1 0 0 0 1 .9h4.8a1 1 0 0 0 1-.9L14 6" stroke="currentColor" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/></svg>
        </button>
      `;
      courseContainer.appendChild(row);
       populateCourseSelects();
    });
    courseContainer.addEventListener('click', function (e) {
      if (e.target.closest('.remove-course-btn')) {
        const row = e.target.closest('.course-row');
        if (courseContainer.children.length > 1) {
          row.remove();
        } else {
          row.querySelector('select').value = '';
        }
      }
    });
  }