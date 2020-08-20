<template>
    <component :is="type" v-bind="linkProps(to)">
        <slot />
    </component>
</template>

<script>
export default {
    props:["to"],
    computed:{
        type(){
            if(this.to && this.to.length > 0){
                return 'router-link'
            }
            return 'a'
        }
    },
    methods:{
        linkProps(to){
            if(this.to && this.to.length > 0){
                return {to:to}
            }
            return {}
        }
    }
}
</script>