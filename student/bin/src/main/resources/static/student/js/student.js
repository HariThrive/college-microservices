$(function () {

  const $tbody = $('#studentTableBody');
  const $form = $('#studentForm');
  const $searchInput = $('#searchInput');

  loadStudents();


  function loadStudents() {
    $.ajax({
      url: '/student/all',
      type: 'GET',
      dataType: 'json',
      success: function (students) {
        renderTable(students || []);
        updateStats(students || []);
      },
      error: function (xhr) {
        console.error(xhr);
        $tbody.html('<tr class="empty-row"><td colspan="7">Could not load students. Check the console for details.</td></tr>');
      }
    });
  }

  function renderTable(students) {
	debugger
    if (!students.length) {
      $tbody.html('<tr class="empty-row"><td colspan="7">No students recorded yet — add one above.</td></tr>');
      return;
    }

    const rows = students.map(function (s) {
      const code = 'STU-' + String(s.id).padStart(2, '0');
      return (
        '<tr>' +
          '<td class="mono">' + escapeHtml(code) + '</td>' +
          '<td class="cell-name">' + escapeHtml(s.name) + '</td>' +
          '<td><span class="badge">' + escapeHtml(s.age) + '</span></td>' +
          '<td class="degree-tag">' + escapeHtml(s.email) + '</td>' +
          '<td class="mono">' + escapeHtml(s.phoneNumber) + '</td>' +
          '<td class="mono">' + escapeHtml(s.departmentName)+'<input type="hidden" class="departmentId" value="' + s.departmentId + '">' + '</td>' +
          '<td class="col-action">' +
            '<button class="icon-btn icon-btn--edit" type="button" title="Edit" ' +
              'data-id="' + s.id + '" data-name="' + escapeAttr(s.name) + '" data-age="' + s.age +
              '" data-email="' + escapeAttr(s.email) + '" data-phone="' + s.phoneNumber +
              '" data-dept="' + s.departmentId + '">' +
              '<svg viewBox="0 0 20 20" fill="none"><path d="M12.5 3.5 16 7l-8.7 8.7-4 1 1-4L12.5 3.5Z" stroke="currentColor" stroke-width="1.4" stroke-linejoin="round"/></svg>' +
            '</button>' +
            '<button class="icon-btn icon-btn--delete" type="button" title="Delete" data-id="' + s.id + '">' +
              '<svg viewBox="0 0 20 20" fill="none"><path d="M4.5 6h11M8 6V4.5h4V6M6 6l.6 9.4a1 1 0 0 0 1 .9h4.8a1 1 0 0 0 1-.9L14 6" stroke="currentColor" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/></svg>' +
            '</button>' +
          '</td>' +
        '</tr>'
      );
    });

    $tbody.html(rows.join(''));
  }

  function updateStats(students) {
    $('#statTotal').text(students.length);

    const avgAge = students.length
      ? Math.round(students.reduce(function (sum, s) { return sum + (Number(s.age) || 0); }, 0) / students.length)
      : 0;
    $('#statAvgAge').text(avgAge);

    const deptCount = new Set(students.map(function (s) { return s.departmentName; })).size;
    $('#statDepartments').text(deptCount);
  }


  $form.on('submit', function (e) {
    e.preventDefault();

    const payload = {
      name: $('#name').val(),
      age: Number($('#age').val()),
      email: $('#email').val(),
      phoneNumber: Number($('#phoneNumber').val()),
      departmentId: Number($('#departmentId').val())
    };

    if (!confirm('Save this student record?')) return;

    $.ajax({
      url: '/student',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(payload),
      beforeSend: function () {
        $('.btn--save').prop('disabled', true);
      },
      success: function () {
        alert('Student saved successfully.');
        $form[0].reset();
        loadStudents();
      },
      error: function (xhr) {
        console.error(xhr);
        alert('Failed to save student.');
      },
      complete: function () {
        $('.btn--save').prop('disabled', false);
      }
    });
  });


  $searchInput.on('input', function () {
    const term = $(this).val().trim().toLowerCase();
    $tbody.find('tr').each(function () {
      const $row = $(this);
      if ($row.hasClass('empty-row')) return;
      $row.toggle($row.text().toLowerCase().includes(term));
    });
  });


  $tbody.on('click', '.icon-btn--edit', function () {
	debugger
    const d = this.dataset;
    $('#name').val(d.name);
    $('#age').val(d.age);
    $('#email').val(d.email);
    $('#phoneNumber').val(d.phone);
    $('#departmentId').val(d.dept);
    $('#name').trigger('focus');
  });


  $tbody.on('click', '.icon-btn--delete', function () {
    if (!confirm('Delete this student?')) return;
    alert('Delete endpoint not implemented yet on the backend.');
  });


  function escapeHtml(value) {
    return String(value === undefined || value === null ? '' : value)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;');
  }

  function escapeAttr(value) {
    return escapeHtml(value).replace(/"/g, '&quot;');
  }

});