import React from "react";
import "./tableHeader-styles.css";

export default class TableHeader extends React.Component{
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="schedule-item">
                <div className="row no-gutters">
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                &nbsp;
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                شنبه
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                یک‌شنبه
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                دوشنبه
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                سه‌شنبه
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index">
                            <span>
                                چهارشنبه
                            </span>
                        </div>
                    </div>
                    <div className="col-day">
                        <div className="schedule-index last-index">
                            <span>
                                پنجشنبه
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}