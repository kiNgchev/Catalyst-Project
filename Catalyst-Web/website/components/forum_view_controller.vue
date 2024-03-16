<template>
    <div class="forum_view_controller">
        <tr>
            <td>{{ thread.title }}</td>
            <br>
            <td>{{ thread.description}}</td>
            <br>
            <td><button>View thread</button></td>
        </tr>
    </div>
</template>
<script>
export default {
    props: {
        thread: Object
    }
}
</script>