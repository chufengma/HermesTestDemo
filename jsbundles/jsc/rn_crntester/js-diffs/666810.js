__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){var t,o;r(d[1])(this,n);for(var l=arguments.length,s=new Array(l),c=0;c<l;c++)s[c]=arguments[c];return(o=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(s))))._intervalID=null,o.state={progress:0},o}return r(d[0])(n,t),r(d[4])(n,[{key:"componentDidMount",value:function(){var t=this;this._intervalID=setInterval(function(){var n=(t.state.progress+.02)%1;t.setState({progress:n})},50)}},{key:"componentWillUnmount",value:function(){null!=this._intervalID&&clearInterval(this._intervalID)}},{key:"render",value:function(){return r(d[5]).createElement(r(d[6]),r(d[7])({progress:this.state.progress},this.props))}}]),n})(r(d[5]).Component),n=(function(n){function o(){return r(d[1])(this,o),r(d[2])(this,r(d[3])(o).apply(this,arguments))}return r(d[0])(o,n),r(d[4])(o,[{key:"render",value:function(){return r(d[5]).createElement(r(d[8]),{title:"ProgressBar Examples"},r(d[5]).createElement(r(d[9]),{title:"Horizontal Indeterminate ProgressBar"},r(d[5]).createElement(r(d[6]),{styleAttr:"Horizontal"})),r(d[5]).createElement(r(d[9]),{title:"Horizontal ProgressBar"},r(d[5]).createElement(t,{styleAttr:"Horizontal",indeterminate:!1})),r(d[5]).createElement(r(d[9]),{title:"Horizontal Black Indeterminate ProgressBar"},r(d[5]).createElement(r(d[6]),{styleAttr:"Horizontal",color:"black"})),r(d[5]).createElement(r(d[9]),{title:"Horizontal Blue ProgressBar"},r(d[5]).createElement(t,{styleAttr:"Horizontal",indeterminate:!1,color:"blue"})))}}]),o})(r(d[5]).Component);e.title='<ProgressBarAndroid>',e.description='Horizontal bar to show the progress of some operation.',e.examples=[{title:'Simple progress bar',render:function(){return r(d[5]).createElement(n,null)}}]},666810,[614,616,617,620,621,70,277,622,666697,666708]);