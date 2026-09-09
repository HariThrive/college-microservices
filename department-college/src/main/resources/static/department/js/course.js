document.addEventListener('DOMContentLoaded', function () {
  const searchInput = document.getElementById('searchInput');
  searchInput.addEventListener('input', function (e) {
    const term = e.target.value.trim().toLowerCase();
    document.querySelectorAll('#courseTableBody tr').forEach(row => {
      if (row.classList.contains('empty-row')) return;
      row.style.display = row.textContent.toLowerCase().includes(term) ? '' : 'none';
    });
  });
});
function editCourse(btn) {
  document.getElementById('courseName').value = btn.dataset.name;
  document.getElementById('description').value = btn.dataset.description;
  document.getElementById('courseName').focus();
}
function deleteCourse(btn) {
  if (!confirm('Delete this course?')) return;
  alert('Delete endpoint not implemented yet on the backend.');
}
$(document).on('submit', '#courseForm', function (e) {
  e.preventDefault();
  const formData = new FormData(this);
  if (confirm('Do you want to save the course?')) {
    $.ajax({
      url: '/course/save',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      cache: false,
      beforeSend: function () {
        $('.btn--save').prop('disabled', true);
      },
      success: function () {
        alert('Course saved successfully.');
        $('#courseForm')[0].reset();
        location.reload();
      },
      error: function (xhr) {
        alert('Failed to save course.');
        console.error(xhr);
      },
      complete: function () {
        $('.btn--save').prop('disabled', false);
      }
    });
  }
});