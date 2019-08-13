__d(function(g,r,i,a,m,e,d){'use strict';e.displayName='ScrollViewExample',e.title='<ScrollView>',e.description='Component that enables scrolling through child components',e.examples=[{title:'<ScrollView>\n',description:'To make content scrollable, wrap it within a <ScrollView> component',render:function(){var t;return r(d[0]).createElement(r(d[1]).View,null,r(d[0]).createElement(r(d[1]).ScrollView,{ref:function(n){t=n},automaticallyAdjustContentInsets:!1,onScroll:function(){},scrollEventThrottle:200,style:c.scrollView},n.map(l)),r(d[0]).createElement(o,{label:"Scroll to top",onPress:function(){t.scrollTo({y:0})}}),r(d[0]).createElement(o,{label:"Scroll to bottom",onPress:function(){t.scrollToEnd({animated:!0})}}),r(d[0]).createElement(o,{label:"Flash scroll indicators",onPress:function(){t.flashScrollIndicators()}}))}},{title:'<ScrollView> (horizontal = true)\n',description:"You can display <ScrollView>'s child components horizontally rather than vertically",render:function(){function t(t,s){var u;return r(d[0]).createElement(r(d[1]).View,{style:s},r(d[0]).createElement(r(d[1]).Text,{style:c.text},t),r(d[0]).createElement(r(d[1]).ScrollView,{ref:function(t){u=t},automaticallyAdjustContentInsets:!1,horizontal:!0,style:[c.scrollView,c.horizontalScrollView]},n.map(l)),r(d[0]).createElement(o,{label:"Scroll to start",onPress:function(){u.scrollTo({x:0})}}),r(d[0]).createElement(o,{label:"Scroll to end",onPress:function(){u.scrollToEnd({animated:!0})}}),r(d[0]).createElement(o,{label:"Flash scroll indicators",onPress:function(){u.flashScrollIndicators()}}))}return r(d[0]).createElement(r(d[1]).View,null,t('LTR layout',{direction:'ltr'}),t('RTL layout',{direction:'rtl'}))}}],e.examples.push({title:'<ScrollView> smooth bi-directional content loading\n',description:"The `maintainVisibleContentPosition` prop allows insertions to either end of the content without causing the visible content to jump. Re-ordering is not supported.",render:function(){var n=6,l=(function(l){function s(){var l,o;r(d[3])(this,s);for(var c=arguments.length,u=new Array(c),h=0;h<c;h++)u[h]=arguments[h];return(o=r(d[4])(this,(l=r(d[5])(s)).call.apply(l,[this].concat(u)))).state={items:r(d[6])(Array(n)).map(function(n,l){return r(d[0]).createElement(t,{msg:"Item "+l})})},o}return r(d[2])(s,l),r(d[7])(s,[{key:"render",value:function(){var l=this;return r(d[0]).createElement(r(d[1]).View,null,r(d[0]).createElement(r(d[1]).ScrollView,{automaticallyAdjustContentInsets:!1,maintainVisibleContentPosition:{minIndexForVisible:1,autoscrollToTopThreshold:10},style:c.scrollView},this.state.items.map(function(t){return r(d[0]).cloneElement(t,{key:t.props.msg})})),r(d[0]).createElement(r(d[1]).ScrollView,{horizontal:!0,automaticallyAdjustContentInsets:!1,maintainVisibleContentPosition:{minIndexForVisible:1,autoscrollToTopThreshold:10},style:[c.scrollView,c.horizontalScrollView]},this.state.items.map(function(t){return r(d[0]).cloneElement(t,{key:t.props.msg,style:null})})),r(d[0]).createElement(r(d[1]).View,{style:c.row},r(d[0]).createElement(o,{label:"Add to top",onPress:function(){l.setState(function(l){var o=n++;return{items:[r(d[0]).createElement(t,{style:{paddingTop:5*o},msg:"Item "+o})].concat(l.items)}})}}),r(d[0]).createElement(o,{label:"Remove top",onPress:function(){l.setState(function(t){return{items:t.items.slice(1)}})}}),r(d[0]).createElement(o,{label:"Change height top",onPress:function(){l.setState(function(t){return{items:[r(d[0]).cloneElement(t.items[0],{style:{paddingBottom:40*Math.random()}})].concat(t.items.slice(1))}})}})),r(d[0]).createElement(r(d[1]).View,{style:c.row},r(d[0]).createElement(o,{label:"Add to end",onPress:function(){l.setState(function(l){return{items:l.items.concat(r(d[0]).createElement(t,{msg:"Item "+n++}))}})}}),r(d[0]).createElement(o,{label:"Remove end",onPress:function(){l.setState(function(t){return{items:t.items.slice(0,-1)}})}}),r(d[0]).createElement(o,{label:"Change height end",onPress:function(){l.setState(function(t){return{items:t.items.slice(0,-1).concat(r(d[0]).cloneElement(t.items[t.items.length-1],{style:{paddingBottom:40*Math.random()}}))}})}})))}}]),s})(r(d[0]).Component);return r(d[0]).createElement(l,null)}});var t=(function(t){function l(){return r(d[3])(this,l),r(d[4])(this,r(d[5])(l).apply(this,arguments))}return r(d[2])(l,t),r(d[7])(l,[{key:"render",value:function(){var t=this.props.source;return r(d[0]).createElement(r(d[1]).View,{style:[c.thumb,this.props.style]},r(d[0]).createElement(r(d[1]).Image,{style:c.img,source:null==t?n[6]:t}),r(d[0]).createElement(r(d[1]).Text,null,this.props.msg))}}]),l})(r(d[0]).PureComponent),n=[r(d[8]),r(d[9]),r(d[10]),r(d[11]),r(d[12]),r(d[13]),r(d[14]),r(d[15]),r(d[16]),r(d[17]),r(d[18]),r(d[19])];n=n.concat(n);var l=function(n,l){return r(d[0]).createElement(t,{key:l,source:n})},o=function(t){var n=t.label,l=t.onPress;return r(d[0]).createElement(r(d[1]).TouchableOpacity,{style:c.button,onPress:l},r(d[0]).createElement(r(d[1]).Text,null,n))},c=r(d[1]).StyleSheet.create({scrollView:{backgroundColor:'#eeeeee',height:300},horizontalScrollView:{height:106},text:{fontSize:16,fontWeight:'bold',margin:5},button:{margin:5,padding:5,alignItems:'center',backgroundColor:'#cccccc',borderRadius:3},row:{flexDirection:'row',justifyContent:'space-around'},thumb:{margin:5,padding:5,backgroundColor:'#cccccc',borderRadius:3,minWidth:96},img:{width:64,height:64}})},666723,[514,516,614,616,617,620,636,621,666685,666686,666687,666688,666689,666690,666691,666692,666693,666694,666695,666696]);