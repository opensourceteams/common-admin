<link rel="stylesheet" href="/static/modules/dataTables/css/jquery.dataTables.min.css" >

<#--<script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
<script src="/static/modules/dataTables/js/jquery.dataTables.min.js"></script>



<script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable({
            "ajax": '/static/project/test/datatables/datatables_data.json'
        });
    } );
</script>

<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </tfoot>
</table>