__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,{style:{marginBottom:10}},r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{values:['One','Two']})),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{values:['One','Two','Three','Four','Five']})))}}]),n})(r(d[5]).Component),n=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{values:['One','Two'],selectedIndex:0})))}}]),n})(r(d[5]).Component),l=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{values:['One','Two'],momentary:!0})))}}]),n})(r(d[5]).Component),o=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{enabled:!1,values:['One','Two'],selectedIndex:1})))}}]),n})(r(d[5]).Component),u=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,{style:{marginBottom:10}},r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{tintColor:"#ff0000",values:['One','Two','Three','Four'],selectedIndex:0})),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{tintColor:"#00ff00",values:['One','Two','Three'],selectedIndex:1})))}}]),n})(r(d[5]).Component),c=(function(t){function n(){var t,l;r(d[1])(this,n);for(var o=arguments.length,u=new Array(o),c=0;c<o;c++)u[c]=arguments[c];return(l=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(u)))).state={values:['One','Two','Three'],value:'Not selected',selectedIndex:void 0},l._onChange=function(t){l.setState({selectedIndex:t.nativeEvent.selectedSegmentIndex})},l._onValueChange=function(t){l.setState({value:t})},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,{style:s.text},"Value: ",this.state.value),r(d[5]).createElement(r(d[6]).Text,{style:s.text},"Index: ",this.state.selectedIndex),r(d[5]).createElement(r(d[6]).SegmentedControlIOS,{values:this.state.values,selectedIndex:this.state.selectedIndex,onChange:this._onChange,onValueChange:this._onValueChange}))}}]),n})(r(d[5]).Component),s=r(d[6]).StyleSheet.create({text:{fontSize:14,textAlign:'center',fontWeight:'500',margin:10}});e.title='<SegmentedControlIOS>',e.displayName='SegmentedControlExample',e.description='Native segmented control',e.examples=[{title:'Segmented controls can have values',render:function(){return r(d[5]).createElement(t,null)}},{title:'Segmented controls can have a pre-selected value',render:function(){return r(d[5]).createElement(n,null)}},{title:'Segmented controls can be momentary',render:function(){return r(d[5]).createElement(l,null)}},{title:'Segmented controls can be disabled',render:function(){return r(d[5]).createElement(o,null)}},{title:'Custom colors can be provided',render:function(){return r(d[5]).createElement(u,null)}},{title:'Change events can be detected',render:function(){return r(d[5]).createElement(c,null)}}]},666725,[614,616,617,620,621,514,516]);