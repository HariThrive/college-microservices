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