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
<style>
.forum_view_controller {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
}
td button {
    border: none;
    width: 100px;
    cursor: pointer;
    border-radius: 10px;
    height: 50px;
    font-size: 1rem;
    font-weight: 700; 
    color: #765D69;
    background-color: #FCD0BA;
    transition-duration: 1s;
}

td button:hover {
    transform: scale(1.1);
    color: #FCD0BA;
    background-color: #765D69;
}

td h1, td, h2 {
    color: white
}
</style>