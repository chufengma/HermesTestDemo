__d(function(g,r,i,a,m,e,d){'use strict';var t=r(d[0]).Picker.Item,l=(function(l){function n(){var t,l;r(d[2])(this,n);for(var c=arguments.length,o=new Array(c),u=0;u<c;u++)o[u]=arguments[u];return(l=r(d[3])(this,(t=r(d[4])(n)).call.apply(t,[this].concat(o)))).state={value:'key1'},l}return r(d[1])(n,l),r(d[5])(n,[{key:"render",value:function(){var l=this;return r(d[6]).createElement(r(d[0]).Picker,{testID:"basic-picker",style:s.picker,selectedValue:this.state.value,onValueChange:function(t){return l.setState({value:t})}},r(d[6]).createElement(t,{label:"hello",value:"key0"}),r(d[6]).createElement(t,{label:"world",value:"key1"}))}}]),n})(r(d[6]).Component),n=(function(l){function n(){var t,l;r(d[2])(this,n);for(var c=arguments.length,o=new Array(c),u=0;u<c;u++)o[u]=arguments[u];return(l=r(d[3])(this,(t=r(d[4])(n)).call.apply(t,[this].concat(o)))).state={value:'key1'},l}return r(d[1])(n,l),r(d[5])(n,[{key:"render",value:function(){return r(d[6]).createElement(r(d[0]).Picker,{style:s.picker,enabled:!1,selectedValue:this.state.value},r(d[6]).createElement(t,{label:"hello",value:"key0"}),r(d[6]).createElement(t,{label:"world",value:"key1"}))}}]),n})(r(d[6]).Component),c=(function(l){function n(){var t,l;r(d[2])(this,n);for(var c=arguments.length,o=new Array(c),u=0;u<c;u++)o[u]=arguments[u];return(l=r(d[3])(this,(t=r(d[4])(n)).call.apply(t,[this].concat(o)))).state={value:'key1'},l}return r(d[1])(n,l),r(d[5])(n,[{key:"render",value:function(){var l=this;return r(d[6]).createElement(r(d[0]).Picker,{style:s.picker,selectedValue:this.state.value,onValueChange:function(t){return l.setState({value:t})},mode:"dropdown"},r(d[6]).createElement(t,{label:"hello",value:"key0"}),r(d[6]).createElement(t,{label:"world",value:"key1"}))}}]),n})(r(d[6]).Component),o=(function(l){function n(){var t,l;r(d[2])(this,n);for(var c=arguments.length,o=new Array(c),u=0;u<c;u++)o[u]=arguments[u];return(l=r(d[3])(this,(t=r(d[4])(n)).call.apply(t,[this].concat(o)))).state={value:'key1'},l}return r(d[1])(n,l),r(d[5])(n,[{key:"render",value:function(){var l=this;return r(d[6]).createElement(r(d[0]).Picker,{style:s.picker,selectedValue:this.state.value,onValueChange:function(t){return l.setState({value:t})},prompt:"Pick one, just one"},r(d[6]).createElement(t,{label:"hello",value:"key0"}),r(d[6]).createElement(t,{label:"world",value:"key1"}))}}]),n})(r(d[6]).Component),u=(function(l){function n(){var t,l;r(d[2])(this,n);for(var c=arguments.length,o=new Array(c),u=0;u<c;u++)o[u]=arguments[u];return(l=r(d[3])(this,(t=r(d[4])(n)).call.apply(t,[this].concat(o)))).state={color:'red'},l}return r(d[1])(n,l),r(d[5])(n,[{key:"render",value:function(){var l=this;return r(d[6]).createElement(r(d[6]).Fragment,null,r(d[6]).createElement(r(d[0]).Picker,{style:[s.picker,{color:'white',backgroundColor:'#333'}],selectedValue:this.state.color,onValueChange:function(t){return l.setState({color:t})},mode:"dropdown"},r(d[6]).createElement(t,{label:"red",color:"red",value:"red"}),r(d[6]).createElement(t,{label:"green",color:"green",value:"green"}),r(d[6]).createElement(t,{label:"blue",color:"blue",value:"blue"})),r(d[6]).createElement(r(d[0]).Picker,{style:s.picker,selectedValue:this.state.color,onValueChange:function(t){return l.setState({color:t})},mode:"dialog"},r(d[6]).createElement(t,{label:"red",color:"red",value:"red"}),r(d[6]).createElement(t,{label:"green",color:"green",value:"green"}),r(d[6]).createElement(t,{label:"blue",color:"blue",value:"blue"})))}}]),n})(r(d[6]).Component),s=r(d[7]).create({picker:{width:100}});e.title='<Picker>',e.description='Provides multiple options to choose from, using either a dropdown menu or a dialog.',e.examples=[{title:'Basic Picker',render:function(){return r(d[6]).createElement(l,null)}},{title:'Disabled Picker',render:function(){return r(d[6]).createElement(n,null)}},{title:'Dropdown Picker',render:function(){return r(d[6]).createElement(c,null)}},{title:'Picker with prompt message',render:function(){return r(d[6]).createElement(o,null)}},{title:'Picker with no listener',render:function(){return r(d[6]).createElement(r(d[6]).Fragment,null,r(d[6]).createElement(r(d[0]).Picker,{style:s.picker},r(d[6]).createElement(t,{label:"hello",value:"key0"}),r(d[6]).createElement(t,{label:"world",value:"key1"})),r(d[6]).createElement(r(d[0]).Text,null,"Cannot change the value of this picker because it doesn't update selectedValue."))}},{title:'Colorful pickers',render:function(){return r(d[6]).createElement(u,null)}}]},666717,[516,614,616,617,620,621,514,71]);