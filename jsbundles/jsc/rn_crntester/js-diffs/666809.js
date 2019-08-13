__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){var t,c;r(d[1])(this,n);for(var o=arguments.length,l=new Array(o),s=0;s<o;s++)l[s]=arguments[s];return(c=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(l)))).state={trueCheckBoxIsOn:!0,falseCheckBoxIsOn:!1},c}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({falseCheckBoxIsOn:n})},style:o.checkbox,value:this.state.falseCheckBoxIsOn}),r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({trueCheckBoxIsOn:n})},value:this.state.trueCheckBoxIsOn}))}}]),n})(r(d[5]).Component),n=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).CheckBox,{disabled:!0,style:o.checkbox,value:!0}),r(d[5]).createElement(r(d[6]).CheckBox,{disabled:!0,value:!1}))}}]),n})(r(d[5]).Component),c=(function(t){function n(){var t,c;r(d[1])(this,n);for(var o=arguments.length,l=new Array(o),s=0;s<o;s++)l[s]=arguments[s];return(c=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(l)))).state={eventCheckBoxIsOn:!1,eventCheckBoxRegressionIsOn:!0},c}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,{style:o.container},r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({eventCheckBoxIsOn:n})},style:o.checkbox,value:this.state.eventCheckBoxIsOn}),r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({eventCheckBoxIsOn:n})},style:o.checkbox,value:this.state.eventCheckBoxIsOn}),r(d[5]).createElement(r(d[6]).Text,null,this.state.eventCheckBoxIsOn?'On':'Off')),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({eventCheckBoxRegressionIsOn:n})},style:o.checkbox,value:this.state.eventCheckBoxRegressionIsOn}),r(d[5]).createElement(r(d[6]).CheckBox,{onValueChange:function(n){return t.setState({eventCheckBoxRegressionIsOn:n})},style:o.checkbox,value:this.state.eventCheckBoxRegressionIsOn}),r(d[5]).createElement(r(d[6]).Text,null,this.state.eventCheckBoxRegressionIsOn?'On':'Off')))}}]),n})(r(d[5]).Component),o=r(d[6]).StyleSheet.create({container:{flexDirection:'row',justifyContent:'space-around'},checkbox:{marginBottom:10}});e.title='<CheckBox>',e.displayName='CheckBoxExample',e.description='Native boolean input',e.examples=[{title:'CheckBoxes can be set to true or false',render:function(){return r(d[5]).createElement(t,null)}},{title:'CheckBoxes can be disabled',render:function(){return r(d[5]).createElement(n,null)}},{title:'Change events can be detected',render:function(){return r(d[5]).createElement(c,null)}},{title:'CheckBoxes are controlled components',render:function(){return r(d[5]).createElement(r(d[6]).CheckBox,null)}}]},666809,[614,616,617,620,621,514,516]);