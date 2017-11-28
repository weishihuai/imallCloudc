import React, { PropTypes, Component } from 'react'
import JspInclude from './JspInclude'

class Footer extends Component {

  render() {
    return (
        <footer id="footer">
          <div className="hide-fixed pull-right pad-rgt">Currently v0.1</div>
          <p className="pad-lft"> 乐商软件 版权所有 Copyright &#0169; 2010-2014 imall.com.cn  iloosen.com All rights reserved. 粤ICP备12070328号</p>
        </footer>
    )
  }
}

export default Footer
