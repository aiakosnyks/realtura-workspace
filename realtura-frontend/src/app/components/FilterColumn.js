"use client"; // Add this line
// components/FilterColumn.js
import React, { useState } from 'react';
import { Input, Select, Slider, Checkbox } from 'antd';
import { useForm, Controller } from 'react-hook-form';

const { Option } = Select;

const FilterColumn = () => {
    const { control, handleSubmit } = useForm();

    const onSubmit = (data) => {
        // Handle form submission and filtering logic here
        console.log(data);
    };

    return (
        <div className="p-4 bg-gray-100 border border-gray-300 rounded-md">
            <h2 className="text-xl font-semibold mb-4">Filters</h2>
            <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
                <Controller
                    name="description"
                    control={control}
                    render={({ field }) => (
                        <Input placeholder="Description" {...field} />
                    )}
                />
                <Controller
                    name="address"
                    control={control}
                    render={({ field }) => (
                        <Input placeholder="Address" {...field} />
                    )}
                />
                <Controller
                    name="priceRange"
                    control={control}
                    render={({ field }) => (
                        <Slider
                            range
                            {...field}
                            min={0}
                            max={1000000}
                            step={1000}
                            marks={{
                                0: '$0',
                                1000000: '$1M',
                            }}
                        />
                    )}
                />
                <Controller
                    name="propertyType"
                    control={control}
                    render={({ field }) => (
                        <Select placeholder="Property Type" {...field}>
                            <Option value="apartment">Apartment</Option>
                            <Option value="house">House</Option>
                            {/* Add more options as needed */}
                        </Select>
                    )}
                />
                <Controller
                    name="heatingType"
                    control={control}
                    render={({ field }) => (
                        <Select placeholder="Heating Type" {...field}>
                            <Option value="central">Central</Option>
                            <Option value="electric">Electric</Option>
                            {/* Add more options as needed */}
                        </Select>
                    )}
                />
                <Controller
                    name="isActive"
                    control={control}
                    render={({ field }) => (
                        <Checkbox {...field}>Active Listings Only</Checkbox>
                    )}
                />
                <button type="submit" className="mt-4 p-2 bg-blue-500 text-white rounded-md">
                    Apply Filters
                </button>
            </form>
        </div>
    );
};

export default FilterColumn;
